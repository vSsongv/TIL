mv 명령어로 이름을 바꾸거나 이동시킬때는 주의해야한다. **git이 delete로 인식하여 연속성이 깨질 수 있기 때문이다.** _한번이라도 commit을 하기 전에는 괜찮다._
![](https://images.velog.io/images/songjy377/post/a5d9b067-e165-463b-917e-e1a7c08c3b37/image.png)

- 이때 git에게 "나 삭제한 거 아니야~이름만 바꾼거야!" 라고 알려줘야하기 때문에 
```git mv [fileName] [targetfolder]``` 로 실행해줘야 한다.

![](https://images.velog.io/images/songjy377/post/e37b5c12-0ccd-4b76-94d8-b000c8326cb0/image.png)

- 잘못 commit한 경우 ```git checkout -- [fileName]``` 으로 바로 이전 commit상태로 rollback가능하다.

![](https://images.velog.io/images/songjy377/post/0bf54a9f-8f4a-4b16-960e-117eada689b4/image.png)

- 이미 git add를 한 파일에 대해서 Unstaging하고 싶으면 ```git reset HEAD [filename]``` 으로 돌려주면 된다.

![](https://images.velog.io/images/songjy377/post/d50abbbf-411b-4954-9ba6-4b9a1ee4296d/image.png)

## Reset Commit
### Worst case: Reset
> - commit message를 수정하고자 할 때는 ```git commit --amend```을 면 바로 commit message를 수정할 수 있게 된다.
![](https://images.velog.io/images/songjy377/post/1b26ebb8-64aa-4f77-9013-8278ffa9edd4/image.png)

- **협업 시 다른 cloned repo에 존재하던 commit log로 인해 파일이 살아나거나, 과거 이력이 깔끔히 사라져 commit log tracking이 힘들어짐.
solution: 잘못한 이력도 commit으로 박제하고 수정한 이력을 남기자!**

### Best case: Revert
>
- 이미 push한 commit도 돌릴 수 있다.
- 현재 HEAD에서 직전의 3개의 commit을 순서대로 거슬러 올라가 해당 내역에 대해 commit, push 수행
- 잘못하기 전 과거로 돌아가 최신을 유지하면서 되돌렸다는 이력을 commit으로 남겨 모든 팀원이 이 사항을 공유하고 주지시킬 수 있음.
- commit을 따로 안할땐 --no-edit
- merge commit을 되돌릴 땐 ```-m($git revert -m {1 or 2} {merge commit id})```
```
   git revert --no-commit HEAD~3..
   git commit
   git push origin <branch>
```
![](https://images.velog.io/images/songjy377/post/bcb7b120-8d06-497d-8dbe-ebc9d414effc/image.png)![](https://images.velog.io/images/songjy377/post/173cb6e4-3adb-4afe-8757-5a4456d64ff4/image.png)