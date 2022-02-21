### Milestone 
- 큰 작업 단위로, 'first Sprint'같은 것이 예시가 될 수 있다.
![](https://images.velog.io/images/songjy377/post/2b41d39a-8adf-4212-8d3e-a8ec244df6e8/image.png)

### Issue
- Milestone의 해야 할 일들 하나하나를 issue로 볼 수 있다. 프로젝트, 레포와 관계된 모든 해야할 일과 버그, 개선사항 등을 기록
![](https://images.velog.io/images/songjy377/post/93b2c173-7a6c-43c1-9961-1a1aed85c0f6/image.png)
- Projects: 해야할 일의 진도에 따른 구성과 우선순위 지정

- clone을 받은 후 git flow init으로 develop branch를 생성한다.
- Issue와 pull request는 동시에 numbering되기 때문에 한 Issue가 완료되었다면 commit message에 issue의 number를 붙여주는것이 좋다.

- 작업이 끝나면 branch는 닫는 것이 좋다.
- git push -u origin develop로 push하자.(두번째 push부터는 -u 안써줘도 된다.) _develop -> develop으로!_
- pull request를 완료할 때 description을 해주는것이 좋다.

- code review에서 3가지 선택자가 있다.(commnet: pass / Approve: 승인 / Request change: 다시 하세용)

- git pull보다는 git fetch + git merge가 필요 없는 코드를 쳐내면서 합칠 수 있기 때문에 좀 더 낫다. 
```
	git fetch origin develop
	git merge FETCH_HEAD
```
- 팀장은 develop branch를 만들어서 나눠줘야한다.
- 팀장은 develop branch로부터 pull을 받으면 되고,
- 팀원은 따로 팀장의 remote를 추가해준 후 pull을 받아야 한다.
```
	git fetch upstream develop
	git merge FETCH_HEAD
```

## team으로 작업하기
pull을 받고 싶으면 팀장의 develop를 내 develop에 받아와야함. 작업을 시작하기 전에 pull꼭 받아야 한다. 

1. 먼저 팀장이 repo를 만든다.
2. 팀원들은 issue를 생성한 후, fork를 한다.
3. fork뜬 repo를 clone한 후, 해당 repo로 이동한 후, gif flow init을 해준다.
4. git remote로 팀장 repo remote를 추가한 후 pull을 받는다.
5. git flow start를 한 후, 개발을 시작한다.
6. finish 후 push를 한다. ```git push -u origin develop```
7. 그 다음 pr을 날린다.

## commit convention
항상 제목과 함께 커밋단위에 대한 설명을 작성하는 것이 좋음.
제목은 클릭을 안 해도 어떤 내용인지 알 수 있도록!
commit 단위는 작을수록 좋음(하나의 클래스 안의 method 단위 정도.)
git flow 쓰자.