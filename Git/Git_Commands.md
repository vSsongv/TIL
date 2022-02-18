### git clone [repo addr]
>다른 repo를 나의 local에 clone할 수 있다.

### git remote add [repo name] [repo address]
- 현재 local repo와 원격 rep의 연결고리를 만들어준다. 

>- git remote -v : 모든 remote 루트 확인 가능

>- git branch -M main : branch 생성 명령어

>- git add [file name] : 해당 file을 push하기 위해 상태 관리 가능 목록(stages)에 추가해준다.
  
>- git push -u [remote name] [branch name] : remote repo에 현재 작업된 branch내용을 push한다. push전까지는 remote repo에 commit내용이 반영되지 않는다.

>- git pull [remote name] : 원격 repo에 변결된 내용을 내 local repo에 업데이트해준다. _이 과정에서 complcit이 발생할 수 있다._

>- **git status** : 현재 add된 파일/파일이 있는 폴더를 확인할 수 있다.

>- git status -uall : add된 파일이 폴더 내에 있을 경우, 이 command를 사용하면 파일명과 함께 보여준다.

### Commit
>
- git commit : stages에 adding된 file들을 commit message와 함께 commit한다.(commit message는 영어로 하는 것이 좋다.) 
  - 맨 위에 brief한 내용을 적고, 아래 부분에 description을 하면 좋다.
- git commit -m "[commit message]" : commit과 똑같은 작업을 한다. ""이후 enter 2번을 입력하면 discription 작성 가능.
- 동작 가능한 최소단위로 자주 commit하는 것이 좋다. 해당 작업단위에 수행된 모든 파일 변화가 해당 commit에 포함되어야 함. ex)method 단위, git clone 부분
- 모두가 이해할 수 있는 log를 작성할 것.
- 제목은 축약하여 쓰되(50자 이내), 내용은 문장형으로 작성하여 추가설명 할 것.
- 제목과 내용은 한 줄 띄워 분리할 것.
  - 내용은 이 commit의 구성과 의도를 충실히 작성할 것.

### ❗❗commit을 할 때는 해당 작업이 어떤 기능인지에 따라 나눠서 commit해 주는것이 중요하다.❗❗

### commit convention
- **feat: features(기능)** -ex)py, .js
- **docs: documentations** -ex) md, function
- **conf: configurations(환경설정 파일들)**
- **test: test**
- **fix: bug-fix**
- **refactor: refactoring**
- ci: Continuous Integration(
- build: Build
- perf: Performance(성능개선)
