## 🔥branch
- 독립적으로 어떤 작업을 진행하기 위한 개념. 각 ranch 내에서 독립적인 상태를 가지고 있다.
- 보통 여러명이서 동시에 작업을 할 때, 다른 사람의 작업에 영향을 주거나 받지 않도록, 먼저 메인 브랜치에서 자신의 작업 전용 브랜치를 만든다. 그리고 각자 작업을 진행한 후, 작업이 끝난 사람은 메인 브랜치에 자신의 브랜치의 변경 사항을 적용한다.
- git branch [branch name] : branch를 생성한다.
- git branch checkout/switch [branch name] : 해당 branch로 이동한다.
- git merge [branch name] : **merge를 할 branch로 이동한 후 실행해야 한다.** branch에서 작업한 내용을 main branch에 합쳐주는 기능이다.
>![](https://images.velog.io/images/songjy377/post/b743a8fa-a2e7-4b0a-adc2-3e4053eb6b89/image.png)
>- 이때 conflict가 발생할 수 있다.
>- conflict난 파일을 확인해 보면
>
>![](https://images.velog.io/images/songjy377/post/7663b18c-3fd7-4356-8c3b-82a1666f70c8/image.png)
>
>- 이런 형태로 나타나는데, 이때 코드를 적절하게 수정해준 후 저장해주자.
> 
>![](https://images.velog.io/images/songjy377/post/ab6a3aa1-39f6-463e-b73b-ee7860855808/image.png)
>- 저장해 준 후 git add -> commit를 하려고 하면 아래 사진처럼 conflict에 대안 commit messages들이 미리 작성되어 있다. 여기에 message를 더해준 후 commit을 하면 된다.
>
> ![](https://images.velog.io/images/songjy377/post/d187f2c6-ce8c-44ae-8da1-d887f4c37d76/image.png)
>
> - branch push는 git push [remote] [branch name] 의 형태로 해준다.
> - branch -d [branch name] : branch를 삭제할 수 있다.
 
## 🔥branching models

- branch를 생성하고, 닫고, 합치고 하는 하나로 묶은 commands를 제송하여 많은 작업은 줄여준다.

- git flow
  - (hotfix)- master -(release)- develop - feature
  - pros: 가장 많이 적용, 각 단계가 명확히 구분
  - cons: 복잡..
- github flow
  - master - feature
  - pros: 브랜치 모델 단순화, master의 모든 커밋은 deployable
  - cons: CI 의존성 높음. 누구 하나라도 실수했다간..(pull request로 방지)
- gitlab flow
  - production - pre-production - master - feature
  - pros: deploy, issue에 대한 대응이 가능하도록 보완
  - cons: git flow와 반대 (master-develop, production-master)
  
## 🔥git flow

![](https://images.velog.io/images/songjy377/post/a2283914-e309-4892-863b-8d45471df2f9/image.png)
  - 참고 사이트 [gitflow](https://danielkummer.github.io/git-flow-cheatsheet/index.ko_KR.html)
  
### 1. init
  >
  - 기존 git 저장소 내에서 초기화하는 것으로 git-flow의 사용을 시작합니다.
  - ```git flow init```

  ![](https://images.velog.io/images/songjy377/post/3c91ece5-195c-4c2f-b785-1351add69544/image.png)
  
### 2. 기능 시작하기
  >
  - 새 기능의 개발은 'develop' 브랜치에서 시작합니다.
  - 다음과 같이 새 기능의 개발을 시작합니다.
  - ```git flow feature start MYFEATURE```
  - 이것은 'develop'에 기반한 새 기능(feature) 브랜치를 생성하고 그 브랜치로 전환합니다.

  ![](https://images.velog.io/images/songjy377/post/22953e7c-0845-4b6d-9a59-58eb9db0c572/image.png)
  
### 3. 기능 완료
  >
  - ```git flow feature finish MYFEATURE```
  - 명령어를 치기 전에 **add와 commit을 진행**해줘야 한다.
  - 기능 개발을 완료합니다. 이것은 다음 작업들을 수행합니다.
  - MYFEATURE 브랜치를 'develop'에 병합(merge)합니다.
  - 기능 브랜치를 삭제합니다.
  - 'develop' 브랜치로 전환합니다.

  ![](https://images.velog.io/images/songjy377/post/b4d4a24f-350d-4594-b84a-483ad7344eb2/image.png)
  
### 5. Release 시작
> `git flow release start RELEASE [BASE]`
  ![](https://images.velog.io/images/songjy377/post/ea944d37-e720-4785-9790-3e838b377ebd/image.png)
  
### 6. Release 완료
  >
  ``` git flow release finish RELEASE ```
  - 다음 단계를 수행합니다. vi창이 나옵니다.
    1. 'release' 브랜치를 'master' 브랜치에 병합(merge)
    2. 릴리스를 릴리스 이름으로 태그(tag) **이 버전을 만들기 위해 어떤 개발을 했는지를 전부 다 적어줘야 합니다.**
    3. 릴리스를 'develop' 브랜치로 재병합(back-merge)
    4. 'release' 브랜치 삭제
  ![](https://images.velog.io/images/songjy377/post/d774f694-b672-4622-abc9-ca2462358bf3/image.png)
  - 그리고 develop, main의 내용을 origin에 push해 줍니다. 
  **git push --tags를 사용해 태그들을 push하는 것을 잊지마세요.**
  