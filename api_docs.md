# 공통 에러 형식

서비스에서 예외 발생 시 해당 에러 형식으로 반환

| **필드** | **타입** | **필수 여부** | **설명** |
| --- | --- | --- | --- |
| timestamp | String | Y | 에러 발생 시점<br>(yyyy-MM-DD'T'HH:MM:SS:ssss) |
| status | String | Y | HTTP 에러 코드 |
| error | String | Y | 에러 코드 |
| message | String | Y | 에러 메세지 |
| path | String | Y | 에러 발생 지점 |

## 에러 코드

| **HTTP 코드** | **에러 코드** | **에러 메시지** |
| --- | --- | --- |
| 400 |  | 인증에 실패했습니다. |
| 401 |  | Authentication header not exists / OAuth 인증 헤더(authorization header)가 없습니다. |
| 403 |  | 호출 권한이 없습니다. |

# 공통 페이징 로직

조회 API에 "**공통 페이징 로직 참조**"라는 문구로 표시.

## 요청 Param

| **query param** | **설명** |
| --- | --- |
| size | 한 페이지 당 조회하는 상품 갯수를 기입한다.<br>(default: 10) |
| sort | 어떤 컬럼을 기준으로 조회하는지 기입한다.<br>- updateAt (default)<br>- createAt<br>- orderStatus<br>- email<br>- address<br>- postcode |
| direction | 오름차순 혹은 내림차순을 기입한다.<br>(ASC, DESC 둘 중 한가지)<br>(default: ASC) |

## 출력 결과

| 필드 | 타입 | 필수 여부 | 설명 |
| --- | --- | --- | --- |
| pageable.pageNumber | int | Y | 현재 페이지 번호 (0부터 시작) |
| pageable.pageSize | int | Y | 페이지당 요소 수 |
| pageable.sort.empty | boolean | Y | 정렬 조건이 비어있는지 여부 |
| pageable.sort.unsorted | boolean | Y | 정렬이 되어 있지 않은지 여부 |
| pageable.sort.sorted | boolean | Y | 정렬이 되어 있는지 여부 |
| pageable.offset | int | Y | 현재 페이지의 시작점 오프셋 |
| pageable.unpaged | boolean | Y | 페이징이 적용되지 않는지 여부 |
| pageable.paged | boolean | Y | 페이징이 적용되었는지 여부 |
| last | boolean | Y | 마지막 페이지 여부 |
| totalElements | int | Y | 전체 요소 수 |
| totalPages | int | Y | 전체 페이지 수 |
| size | int | Y | 페이지당 요소 수 (동일한 값) |
| number | int | Y | 현재 페이지 번호 (동일한 값) |
| sort.empty | boolean | Y | 정렬 조건이 비어있는지 여부 (동일한 값) |
| sort.unsorted | boolean | Y | 정렬이 되어 있지 않은지 여부 (동일한 값) |
| sort.sorted | boolean | Y | 정렬이 되어 있는지 여부 (동일한 값) |
| first | boolean | Y | 첫 번째 페이지 여부 |
| numberOfElements | int | Y | 현재 페이지에 있는 요소 수 |
| empty | boolean | Y | 현재 페이지가 비어있는지 여부 |

---

# 상품 추가 API

## API 기본 정보

| **메서드** | **요청 URL** | **출력 포맷** | **설명** |
| --- | --- | --- | --- |
| GET | /product/add | JSON | 커피 상품 추가 |

## 요청 바디

```json
{
	"productName": String,
	"catagory": String,
	"price": long,
	"description": String,
	"image_url": String
}
```

## 출력 결과

| **필드** | **타입** | **필수 여부** | **설명** |
| --- | --- | --- | --- |
| productId | UUID | Y |  |
| productName | String | Y | 호출 결과 메시지 |
| category | String | Y | 상품 카테고리<br>(현재는 COFFEE, OTHER가 있습니다.) |
| price | String | Y | 상품 가격 |
| stock | String | Y | 재고 |
| description | String | Y | 상품 설명 |
| image_url | String | Y | 이미지 URL |
| createdAt | String | Y | 상품 생성 날짜<br>(yyyy-MM-DD'T'HH:MM:SS:ssss) |
| updatedAt | String | Y | 상품 수정 날짜<br>(yyyy-MM-DD'T'HH:MM:SS:ssss) |

## 예시

### 요청 예시

```json
POST <http://localhost:8080/product/add>
Content-Type: application/json

{
  "productName": "test_coffee",
  "catagory": "COFFEE",
  "price": 4500,
  "description": "test description",
  "image_url": "test_url"
}

```

### 응답 예시

```json
HTTP/1.1 200
Content-Type: application/json

{
  "productId": "1e738534-41a1-4108-94f8-670b3f47702f",
  "productName": "test_coffee",
  "category": "COFFEE",
  "price": 4500,
  "stock": 0,
  "description": "test description",
  "image_url": "test_url",
  "createdAt": "2024-09-11T16:39:45.9073",
  "updatedAt": "2024-09-11T16:39:45.9073"
}

```

# 상품 수정 API

## API 기본 정보

| **메서드** | **요청 URL** | **출력 포맷** | **설명** |
| --- | --- | --- | --- |
| PUT | /product/update | JSON | 커피 상품 수정 |

## 요청 바디

```json
{
	"id": UUID
	"productName": String,
	"catagory": String,
	"price": long,
	"description": String,
	"image_url": String
}

```

## 출력 결과

| **필드** | **타입** | **필수 여부** | **설명** |
| --- | --- | --- | --- |
| productId | UUID | Y |  |
| productName | String | Y | 호출 결과 메시지 |
| category | String | Y | 상품 카테고리<br>(현재는 COFFEE, OTHER가 있습니다.) |
| price | String | Y | 상품 가격 |
| stock | String | Y | 재고 |
| description | String | Y | 상품 설명 |
| image_url | String | Y | 이미지 URL |
| createdAt | String | Y | 상품 생성 날짜<br>(yyyy-MM-DD'T'HH:MM:SS:ssss) |
| updatedAt | String | Y | 상품 수정 날짜<br>(yyyy-MM-DD'T'HH:MM:SS:ssss) |

## 예시

상품 생성 API와 동일

# 상품 삭제 API

## API 기본 정보

| **메서드** | **요청 URL** | **출력 포맷** | **설명** |
| --- | --- | --- | --- |
| DELETE | /product/delete | JSON | 커피 상품 삭제 |

## 요청 Param

| **query param** | **설명** |
| --- | --- |
| **id** | 삭제할 상품 id 기입<br>UUID 형태로 기입한다. |

## 출력 결과

```json
{
  "result": "success"
}

```

## 예시

```
DELETE <http://localhost:8080/product/delete?id={uuid}>

```

# 상품 조회 API

## API 기본 정보

| **메서드** | **요청 URL** | **출력 포맷** | **설명** |
| --- | --- | --- | --- |
| GET | /product/list | JSON | 커피 상품 페이징 조회 |

## 요청 Param

**공통 페이징 로직 참조**

## 출력 결과

| **필드** | **타입** | **필수 여부** | **설명** |
| --- | --- | --- | --- |
| content | JSON | Y | 상품 정보 배열 |
| pageable | String | Y | 페이징 |

## 예시

### 응답

```
{
  "content": [
    {
      "productId": "b8506bb7-eb5d-47a8-81a3-4747bb767624",
      "productName": "디카페인 하우스 블렌드",
      "catagory": "COFFEE",
      "price": 11000,
      "description": "깔끔하고 균형 잡힌 맛이 특징인 중간 정도 무게감의 커피",
      "image_url": "<https://image.istarbucks.co.kr/upload/store/skuimg/2023/11/[11059722]_20231110094425022.jpg>"
    },
    {
      "productId": "500acc6d-2a87-4f3b-bd6d-d2bfcb720ea9",
      "productName": "발리 마운트 바투르",
      "catagory": "COFFEE",
      "price": 18500,
      "description": "향긋하고 풋풋한 레몬 잎의 뉘앙스,달콤한 마지팬의 풍미와 코코아 파우더의 쌉쌀한 피니시",
      "image_url": "<https://image.istarbucks.co.kr/upload/store/skuimg/2024/05/[11146733]_20240510150718263.jpg>"
    },
    ...
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "sort": {
      "empty": false,
      "unsorted": false,
      "sorted": true
    },
    "offset": 0,
    "unpaged": false,
    "paged": true
  },
  "last": false,
  "totalElements": 25,
  "totalPages": 3,
  "size": 10,
  "number": 0,
  "sort": {
    "empty": false,
    "unsorted": false,
    "sorted": true
  },
  "first": true,
  "numberOfElements": 10,
  "empty": false
}

```

## API 기본 정보

| **메서드** | **요청 URL** | **출력 포맷** | **설명** |
| --- | --- | --- | --- |
| GET | /product | JSON | 커피 상품 상세 정보 조회 |

## 요청 Path

| **path variable** | **설명** |
| --- | --- |
| id | 조회할 상품 id 기입<br>UUID 형태로 기입한다. |

## 출력 결과

| **필드** | **타입** | **필수 여부** | **설명** |
| --- | --- | --- | --- |
| productId | UUID | Y |  |
| productName | String | Y | 호출 결과 메시지 |
| category | String | Y | 상품 카테고리<br>(현재는 COFFEE, OTHER가 있습니다.) |
| price | String | Y | 상품 가격 |
| stock | String | Y | 재고 |
| description | String | Y | 상품 설명 |
| image_url | String | Y | 이미지 URL |
| createdAt | String | Y | 상품 생성 날짜<br>(yyyy-MM-DD'T'HH:MM:SS:ssss) |
| updatedAt | String | Y | 상품 수정 날짜<br>(yyyy-MM-DD'T'HH:MM:SS:ssss) |

## 예시

### 요청 예시

```
GET <http://localhost:8080/product/91cfbe96-7dfc-45fd-bddc-8c4c1ba0f2c9>

```

### 응답 예시

```json
HTTP/1.1 200
Content-Type: application/json

{
  "productId": "1e738534-41a1-4108-94f8-670b3f47702f",
  "productName": "test_coffee",
  "category": "COFFEE",
  "price": 4500,
  "stock": 0,
  "description": "test description",
  "image_url": "test_url",
  "createdAt": "2024-09-11T16:39:45.9073",
  "updatedAt": "2024-09-11T16:39:45.9073"
}

```

---

# 주문 페이징 조회 API

## API 기본 정보

| **메서드** | **요청 URL** | **출력 포맷** | **설명** |
| --- | --- | --- | --- |
| GET | /order/list | JSON | 주문 페이징 조회 |

## 출력 결과

| **필드** | **타입** | **필수 여부** | **설명** |
| --- | --- | --- | --- |
| content | JSON | Y | 주문 및 주문 상품 정보 배열 |

## 주문 정보

| **필드** | **타입** | **필수 여부** | **설명** |
| --- | --- | --- | --- |
| orderId | JSON | Y | 주문 및 주문 상품 정보 배열 |
| email | String | Y | 이메일 |
| address | String | Y | 주소 |
| postcode | String | Y | 우편번호 |
| orderStatus | String | Y | 주문 상태<br>- ACCEPTED<br>- PAYMENT_CONFIRMED<br>- READY_FOR_DELIVERY<br>- SHIPPED<br>- SETTLED<br>- CANCELLED |
| orderItems | JSON | Y | 주문 상품 내역 배열 |
| createdAt | String | Y | 주문 생성 날짜 |
| updatedAt | String | Y | 주문 업데이트 날짜 |

## 주문 상품

| **필드** | **타입** | **필수 여부** | **설명** |
| --- | --- | --- | --- |
| **seq** | long | Y | 주문 상품 번호 |
| **products** | JSON | Y | 상품 정보 배열<br>상품 API와 동일하니 상품 API 참고 |
| **category** | String | Y | 카테고리 |
| **price** | long | Y | 상품 가격<br>주문 시점의 가격 기준 |
| **quantity** | int | Y | 주문 상품 갯수 |
| **createdAt** | String | Y | 주문 상품 생성 날짜 |
| **updatedAt** | String | Y | 주문 상품 업데이트 날짜 |

## 예시

### 요청

```json
GET <http://localhost:8080/order/list?size=3&sort=updatedAt&direction=DESC>
```

### 응답

```
{
  "content": [
    {
      "orderId": "0560cc64-3f77-4e91-9fae-c0c81acbf9c9",
      "email": "test@naver.com",
      "address": "testtest",
      "postcode": "43125791",
      "orderStatus": "ACCEPTED",
      "orderItems": [
        {
          "seq": 202,
          "products": {
            "productId": "b459bb2a-c3bd-4619-84f9-35ff86965277",
            "productName": "홀리데이 블렌드",
            "category": "COFFEE_BEAN_PACKAGE",
            "price": 12990,
            "stock": 20,
            "description": "특별한 블렌드로 커피 하우스와 동일한 품질의 100% 아라비카 원두로 만든 허브와 메이플 향의 균형 잡힌 달콤함을 즐겨보세요",
            "image_url": "<https://www.starbucksathome.com/kr/sites/default/files/styles/pdp_gallery_large/public/2022-09/SBUX_WB_Holiday_Website_7613039399712_ContactShadow_1656577608358.png?h=07cea122&itok=0LODgoMX>",
            "createdAt": "2024-09-09T10:15:12.675979",
            "updatedAt": "2024-09-11T16:29:57.547134"
          },
          "category": "커피",
          "price": 12990,
          "quantity": 1,
          "createdAt": "2024-09-09T11:15:48.829903",
          "updatedAt": "2024-09-09T12:35:09.959337"
        },
        {
          "seq": 203,
          "products": {
            "productId": "50410cab-9ef0-4c8b-9a5d-99072b811707",
            "productName": "스타벅스® 블론드 에스프레소",
            "category": "COFFEE_BEAN_PACKAGE",
            "price": 17000,
            "stock": 50,
            "description": "부드럽고 풍부한 풍미. 가장 라이트한 에스프레소 로스트",
            "image_url": "<https://www.starbucksathome.com/kr/sites/default/files/styles/pdp_gallery_xlarge/public/2021-05/SBUX_Website_WB_longShadow_BlondeEspresso%20%281%29%20%281%29%20%281%29%20%281%29%20%281%29%20%281%29%20%281%29%20%281%29%20%282%29.png?h=07cea122&itok=D4IxtFm7>",
            "createdAt": "2024-09-09T10:12:01.148863",
            "updatedAt": "2024-09-09T10:13:45.523926"
          },
          "category": "커피",
          "price": 17000,
          "quantity": 2,
          "createdAt": "2024-09-09T11:15:48.865626",
          "updatedAt": "2024-09-09T12:35:10.072613"
        },
        {
          "seq": 204,
          "products": {
            "productId": "1e6f55a6-ff4b-4387-8090-d94f5cec6484",
            "productName": "스타벅스® 브렉퍼스트 블렌드",
            "category": "COFFEE_BEAN_PACKAGE",
            "price": 11990,
            "stock": 100,
            "description": "산뜻한 시트러스 향으로 마무리 되는 끝 맛으로 부드러우면서도 활기찬 한 잔의 컵을 완성합니다.",
            "image_url": "<https://www.starbucksathome.com/kr/sites/default/files/styles/pdp_gallery_xlarge/public/2023-03/SBUX_WB_Breakfast-Blend_Website_8934804047300_LongShadow_1842by1542px_221215_1_1671513801594.png?h=07cea122&itok=b_EBQBPr>",
            "createdAt": "2024-09-09T10:10:37.889457",
            "updatedAt": "2024-09-09T10:10:37.889457"
          },
          "category": "커피",
          "price": 11990,
          "quantity": 3,
          "createdAt": "2024-09-09T11:15:48.903166",
          "updatedAt": "2024-09-09T12:35:10.186071"
        }
      ],
      "createdAt": "2024-09-09T11:15:48.785567",
      "updatedAt": "2024-09-09T11:33:48.045074"
    },
    ...
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 3,
    "sort": {
      "empty": false,
      "unsorted": false,
      "sorted": true
    },
    "offset": 0,
    "unpaged": false,
    "paged": true
  },
  "last": false,
  "totalElements": 26,
  "totalPages": 9,
  "size": 3,
  "number": 0,
  "sort": {
    "empty": false,
    "unsorted": false,
    "sorted": true
  },
  "first": true,
  "numberOfElements": 3,
  "empty": false
}

```

# 주문 email 별 페이징 조회 API

## API 기본 정보

| **메서드** | **요청 URL** | **출력 포맷** | **설명** |
| --- | --- | --- | --- |
| GET | /order/listEmail | JSON | email 검색 페이징 조회 |

## 요청 Param

**공통 페이징 로직 참조**

| **query param** | **설명** |
| --- | --- |
| **email** | 이메일 형식으로 기입한다 |

## 출력 결과

주문 페이징 조회 API와 동일

# 주문 상태 별 페이징 조회 API

## API 기본 정보

| **메서드** | **요청 URL** | **출력 포맷** | **설명** |
| --- | --- | --- | --- |
| GET | /order/listStatus | JSON | 주문 상태 검색 페이징 조회 |

## 요청 Param

**공통 페이징 로직 참조**

| query param | 설명 |
| --- | --- |
| status | 주문 상태<br>- ACCEPTED<br>- PAYMENT_CONFIRMED<br>- READY_FOR_DELIVERY<br>- SHIPPED<br>- SETTLED<br>- CANCELLED |

## 출력 결과

주문 페이징 조회 API와 동일

# 주문하기 API

## API 기본 정보

| **메서드** | **요청 URL** | **출력 포맷** | **설명** |
| --- | --- | --- | --- |
| POST | /order/add | JSON | 주문하기 |

## 요청 바디

```json
{
  "email": String,
  "address": String,
  "postcode": String,
  "orderItems": [{
    "id": UUID
    "quantity": int
  }]
}

```

## 출력 결과

| **필드** | **타입** | **필수 여부** | **설명** |
| --- | --- | --- | --- |
| orderId | UUID | Y | 주문 아이디 |

## 예시

### 요청

```json
POST <http://localhost:8080/order/add>
Content-Type: application/json

{
  "email": "test@example.com",
  "address": "서울특별시",
  "postcode": "123123",
  "orderItems": [{
    "id": "f81f4830-48ea-4f95-9c89-2adab7bbae05",
    "quantity": 2
  }]
}

```

### 응답

```
"5c600495-bb7d-4581-9720-9201b3d49e83"

```

# 주문 수정 API

## API 기본 정보

| 메서드 | 요청 URL | 출력 포맷 | 설명 |
| --- | --- | --- | --- |
| PUT | /order/update | JSON | 주문 수정 |

## 요청 바디

```json
{
  "id": UUID,
  "email": String,
  "address": String,
  "postcode": String
}

```

## 출력 결과

## 주문

| 필드 | 타입 | 필수 여부 | 설명 |
| --- | --- | --- | --- |
| orderId | JSON | Y | 주문 및 주문 상품 정보 배열 |
| email | String | Y | 이메일 |
| address | String | Y | 주소 |
| postcode | String | Y | 우편번호 |
| orderStatus | String | Y | 주문 상태<br>- ACCEPTED<br>- PAYMENT_CONFIRMED<br>- READY_FOR_DELIVERY<br>- SHIPPED<br>- SETTLED<br>- CANCELLED |
| orderItems | JSON | Y | 주문 상품 내역 배열 |
| createdAt | String | Y | 주문 생성 날짜 |
| updatedAt | String | Y | 주문 업데이트 날짜 |

## 주문 상품

| **필드** | **타입** | **필수 여부** | **설명** |
| --- | --- | --- | --- |
| seq | long | Y | 주문 상품 번호 |
| products | JSON | Y | 상품 정보 배열<br>상품 API와 동일하니 상품 API 참고 |
| category | String | Y | 카테고리 |
| price | long | Y | 상품 가격<br>주문 시점의 가격 기준 |
| quantity | int | Y | 주문 상품 갯수 |
| createdAt | String | Y | 주문 상품 생성 날짜 |
| updatedAt | String | Y | 주문 상품 업데이트 날짜 |

## 예시

### 요청

```json
PUT <http://localhost:8080/order/update>
Content-Type: application/json

{
  "id": "5c600495-bb7d-4581-9720-9201b3d49e83",
  "email": "test@example.com",
  "address": "남천동",
  "postcode": "123123"
}

```

### 응답

```json
{
  "orderId": "5c600495-bb7d-4581-9720-9201b3d49e83",
  "email": "test@example.com",
  "address": "남천동",
  "postcode": "123123",
  "orderStatus": "ACCEPTED",
  "orderItems": [
    {
      "seq": 702,
      "products": {
        "productId": "f81f4830-48ea-4f95-9c89-2adab7bbae05",
        "productName": "재고 테스트 용",
        "category": "COFFEE_BEAN_PACKAGE",
        "price": 1000,
        "stock": 3,
        "description": "테스트용 ",
        "image_url": "<https://www.starbucksathome.com/kr/sites/default/files/styles/pdp_gallery_large/public/2022-09/SBUX_WB_Holiday_Website_7613039399712_ContactShadow_1656577608358.png?h=07cea122&itok=0LODgoMX>",
        "createdAt": "2024-09-11T16:33:29.111622",
        "updatedAt": "2024-09-11T17:34:19.641097"
      },
      "category": "COFFEE_BEAN_PACKAGE",
      "price": 1000,
      "quantity": 2,
      "createdAt": "2024-09-11T18:08:52.518419",
      "updatedAt": "2024-09-11T18:08:52.518419"
    }
  ],
  "createdAt": "2024-09-11T18:08:52.505877",
  "updatedAt": "2024-09-11T18:13:43.960634"
}

```

# 주문 상태 수정 API

## API 기본 정보

| **메서드** | **요청 URL** | **출력 포맷** | **설명** |
| --- | --- | --- | --- |
| PUT | /order/updateStatus | JSON | 주문하기 |

## 요청 Param

| query param | 설명 |
| --- | --- |
| id | 주문 UUID |
| status | 주문 상태<br>- ACCEPTED<br>- PAYMENT_CONFIRMED<br>- READY_FOR_DELIVERY<br>- SHIPPED<br>- SETTLED<br>- CANCELLED |

## 출력 결과

주문 수정 API와 동일

## 예시

### 요청

```json
PUT <http://localhost:8080/order/updateStatus>?
    id=5c600495-bb7d-4581-9720-9201b3d49e83&
    status=PAYMENT_CONFIRMED

```

# 주문 상품 수량 수정 API

## API 기본 정보

| **메서드** | **요청 URL** | **출력 포맷** | **설명** |
| --- | --- | --- | --- |
| PUT | /orderItem/update | JSON | 주문 상품 수량 수정 |

## 요청 바디

```json
[
  {
    "id": long,
    "quantity": int
  },
  ...
]

```

## 출력 결과

```json
{
  "result": "success"
}

```

## 예시

요청

```json
PUT <http://localhost:8080/orderItem/update>
Content-Type: application/json

[
  {    "id": 702,
    "quantity": 1
  }
]

```

### 응답

```json
{
  "result": "success"
}

```

# 주문 취소 API

## API 기본 정보

| **메서드** | **요청 URL** | **출력 포맷** | **설명** |
| --- | --- | --- | --- |
| **GET** | /order/cancle | JSON | 주문 취소 |

## 요청 Param

## 예시

### 요청

```
GET <http://localhost:8080/order/cancle>?
    id=5c600495-bb7d-4581-9720-9201b3d49e83

```

### 응답

```json
{
  "result": "success"
}
```
