# 서비스 설명
여러가지 기능으로 사용자의 일정을 **효율적으로 관리** 할 수 있는 서비스 입니다. 

## ToDo 리스트
사용자의 일정을 관리할 수 있는 서비스로 **우선 사항(Priority)**, **상태(Status)**, **마감 시간**, **제목 및 상세 내용**을 설정 할 수 있습니다.

### 우선 사항(Priority)
사용자의 일정의 **우선 사항(중요도)**을 나타냅니다. HIGH, MEDIUM, LOW, NONE의 값이 존재하며 의미 하는 바는 다음과 같습니다.

- **HIGH:** 우선 순위가 **가장 높은** 일정
- **MEDIUM:** 우선 순위가 **중간**인 일정
- **LOW:** 우선 순위가 **가장 낮은** 일정
- **NONE:** 우선 순위를 아직 **정하지 않은** 일정

### 상태(Status)
사용자의 일정의 **진행 상태**를 나타냅니다. NOT_STARTED, IN_PROGRESS, COMPLETED의 값이 존재하며 의미 하는 바는 다음과 같습니다.

- **NOT_STARTED:** 아직 일정을 **시작하지** 않음
- **IN_PROGRESS:** 일정을 **진행 중**에 있음
- **COMPLETED:** 일정을 **마무리**함

### 마감 시간
일정을 **효율적으로 관리**가 가능하도록 해당 일정의 마감 시간 설정이 가능합니다.

### 제목 및 상세 내용
일정의 **자세한 내용도 관리**가 가능하도록 해당 일정의 제목과 상세 내용 설정이 가능합니다.


## Sub ToDo 리스트
사용자의 세부 일정을 관리할 수 있는 서비스로 Parent(상위 일정)의 하위 일정으로 관리 됩니다. 세부 일정은 **Status**와 **제목 및 상세 내용**으로 관리가 가능합니다.


<br><br>


# 실행 방법 메뉴얼
## Docker 활용
**실행 방법**
```
docker-compose up -d
```

<br>

⚠️ **경고:** 해당 URL은 docker-compose를 **로컬에서 실행**했을 때의 URL 입니다.

### Backend(container: backend)
**URL:** http://localhost:8080

**Swagger:** http://localhost:8080/swagger-ui/index.html

### Frontend(container: frontend)
**URL:** http://localhost:3000
'
### 인프라
![img](./infra.png)
FrontEnd 앞에 Nginx를 둠으로써 리버스 프록시와 접근 속도를 개선했습니다.
- **리버스 프록시 역할:** 백엔드 서버 정보를 숨기고, 클라이언트가 직접 접근하지 않도록 보호
- **접근 속도 개선:** 정적 파일을 빠르게 서빙하고, 캐싱을 활용해 응답 속도 향상
