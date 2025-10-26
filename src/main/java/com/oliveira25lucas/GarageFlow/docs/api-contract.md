# GarageFlow API Contract (draft v0.1.0)

Este documento descreve o contrato inicial da API pública do GarageFlow.

## Convenções Gerais

* Base URL de todas as rotas: `/api/v1` (versionamento via path).
* Formato de troca: `application/json`.
* Datas e horários em ISO-8601 (`YYYY-MM-DD` para datas simples e `YYYY-MM-DDTHH:MM:SS-03:00` para data/hora).
* Paginação e ordenação seguem o padrão Spring Data (`page`, `size`, `sort`).

### Status codes esperados

* `200 OK` – requisição bem-sucedida (GET).
* `201 Created` – registro criado (POST).
* `400 Bad Request` – erro de validação/formato inválido.
* `404 Not Found` – recurso não encontrado.
* `500 Internal Server Error` – erro inesperado.

### Formato de erro padrão (`ErrorResponse`)

Todos os erros retornam um JSON padronizado:

```json
{
  "timestamp": "2025-10-26T13:45:00-03:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid date format. Use YYYY-MM-DD.",
  "path": "/api/v1/availability"
}
```

---

## 1. Serviços oferecidos pela oficina

### GET `/api/v1/services`

Retorna a lista de serviços disponíveis (troca de óleo, revisão, etc).

Suporta paginação e ordenação opcionais:

* `page` (default `0`)
* `size` (default `20`)
* `sort` (ex: `name,asc` ou `price,desc`)

#### Exemplo de request

```http
GET /api/v1/services?page=0&size=2&sort=price,asc
```

#### Exemplo de resposta `200 OK`

```json
{
  "content": [
    {
      "id": 1001,
      "name": "Troca de Óleo",
      "durationMinutes": 45,
      "price": 149.90
    },
    {
      "id": 1002,
      "name": "Alinhamento e Balanceamento",
      "durationMinutes": 60,
      "price": 199.00
    }
  ],
  "page": 0,
  "size": 2,
  "totalElements": 3,
  "totalPages": 2,
  "sort": "price,asc"
}
```

---

## 2. Mecânicos disponíveis

### GET `/api/v1/mechanics`

Retorna a lista de mecânicos e suas especialidades.

Paginação e ordenação opcionais:

* `page` (default `0`)
* `size` (default `20`)
* `sort` (ex: `name,asc` ou `active,desc`)

#### Exemplo de request

```http
GET /api/v1/mechanics?size=3&sort=name,asc
```

#### Exemplo de resposta `200 OK`

```json
{
  "content": [
    {
      "id": 2001,
      "name": "Ana Souza",
      "specialties": ["Revisão", "Freios"],
      "active": true
    },
    {
      "id": 2002,
      "name": "Carlos Lima",
      "specialties": ["Suspensão", "Direção"],
      "active": true
    },
    {
      "id": 2003,
      "name": "Beatriz N.",
      "specialties": ["Elétrica"],
      "active": false
    }
  ],
  "page": 0,
  "size": 3,
  "totalElements": 3,
  "totalPages": 1,
  "sort": "name,asc"
}
```

Possíveis respostas de erro:

* `500 Internal Server Error` + `ErrorResponse`

---

## 3. Disponibilidade de agenda

### GET `/api/v1/availability?date=YYYY-MM-DD`

Retorna os slots de horário disponíveis para agendamento em uma data específica.

Parâmetros obrigatórios:

* `date` – data desejada no formato `YYYY-MM-DD`.

#### Exemplo de request

```http
GET /api/v1/availability?date=2025-10-27
```

#### Exemplo de resposta `200 OK`

```json
{
  "date": "2025-10-27",
  "slots": [
    {
      "mechanicId": 2001,
      "mechanicName": "Ana Souza",
      "serviceId": 1002,
      "serviceName": "Alinhamento e Balanceamento",
      "startTime": "2025-10-27T09:00:00-03:00",
      "durationMinutes": 60
    },
    {
      "mechanicId": 2002,
      "mechanicName": "Carlos Lima",
      "serviceId": 1001,
      "serviceName": "Troca de Óleo",
      "startTime": "2025-10-27T10:30:00-03:00",
      "durationMinutes": 45
    }
  ]
}
```

#### Respostas de erro

* `400 Bad Request` (ex: data inválida)

```json
{
  "timestamp": "2025-10-26T13:45:00-03:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid date format. Use YYYY-MM-DD.",
  "path": "/api/v1/availability"
}
```

---

## 4. Agendamentos (appointments)

### 4.1 GET `/api/v1/appointments`

Retorna os agendamentos existentes (agenda futura e passada).

Paginação e ordenação opcionais:

* `page` (default `0`)
* `size` (default `20`)
* `sort` (ex: `startTime,asc` ou `status,desc`)

#### Exemplo de request

```http
GET /api/v1/appointments?page=0&size=1&sort=startTime,asc
```

#### Exemplo de resposta `200 OK`

```json
{
  "content": [
    {
      "appointmentId": 9001,
      "customerName": "João Silva",
      "customerPhone": "+55 31 99999-0000",
      "mechanicId": 2001,
      "mechanicName": "Ana Souza",
      "serviceId": 1002,
      "serviceName": "Alinhamento e Balanceamento",
      "startTime": "2025-10-27T09:00:00-03:00",
      "status": "SCHEDULED"
    }
  ],
  "page": 0,
  "size": 1,
  "totalElements": 1,
  "totalPages": 1,
  "sort": "startTime,asc"
}
```

### 4.2 POST `/api/v1/appointments`

Cria um novo agendamento na agenda da oficina.

#### Corpo da requisição

```json
{
  "customerName": "João Silva",
  "customerPhone": "+55 31 99999-0000",
  "serviceId": 1002,
  "mechanicId": 2001,
  "startTime": "2025-10-27T09:00:00-03:00"
}
```

Campos obrigatórios:

* `customerName` (string)
* `customerPhone` (string)
* `serviceId` (long)
* `mechanicId` (long)
* `startTime` (ISO datetime)

#### Resposta `201 Created`

```json
{
  "appointmentId": 9002,
  "customerName": "João Silva",
  "customerPhone": "+55 31 99999-0000",
  "mechanicId": 2001,
  "mechanicName": "Ana Souza",
  "serviceId": 1002,
  "serviceName": "Alinhamento e Balanceamento",
  "startTime": "2025-10-27T09:00:00-03:00",
  "status": "SCHEDULED"
}
```

#### Respostas de erro

* `400 Bad Request` – conflito de horário, dados inválidos etc.
* `404 Not Found` – mecânico ou serviço inexistente.

```json
{
  "timestamp": "2025-10-26T13:50:00-03:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Mechanic 2001 is not available at 2025-10-27T09:00:00-03:00",
  "path": "/api/v1/appointments"
}
```

---

## 5. Paginação e Ordenação

Todos os endpoints de listagem aceitam estes parâmetros de URL:

* `page`  → número da página (0-based).
* `size`  → tamanho da página.
* `sort`  → campo e direção. Formato: `campo,direcao`. Ex.:
    * `name,asc`
    * `price,desc`
    * múltiplas ordenações: `sort=name,asc&sort=price,desc`

### Exemplo completo

```http
GET /api/v1/services?page=1&size=10&sort=price,desc&sort=name,asc
```

A resposta sempre inclui metadados:

* `page`
* `size`
* `totalElements`
* `totalPages`
* `sort`

Isso permite o front montar os controles de navegação.

---

## 6. Próximos passos (não implementado ainda)

* `PUT /api/v1/appointments/{id}` para remarcar / atualizar status.
* `DELETE /api/v1/appointments/{id}` para cancelar.
* Autenticação/autorização (admin x público).
* Padronizar `ErrorResponse` como objeto Java e `@ControllerAdvice` global.
