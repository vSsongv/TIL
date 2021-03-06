## ๐ฅbranch
- ๋๋ฆฝ์ ์ผ๋ก ์ด๋ค ์์์ ์งํํ๊ธฐ ์ํ ๊ฐ๋. ๊ฐ ranch ๋ด์์ ๋๋ฆฝ์ ์ธ ์ํ๋ฅผ ๊ฐ์ง๊ณ  ์๋ค.
- ๋ณดํต ์ฌ๋ฌ๋ช์ด์ ๋์์ ์์์ ํ  ๋, ๋ค๋ฅธ ์ฌ๋์ ์์์ ์ํฅ์ ์ฃผ๊ฑฐ๋ ๋ฐ์ง ์๋๋ก, ๋จผ์  ๋ฉ์ธ ๋ธ๋์น์์ ์์ ์ ์์ ์ ์ฉ ๋ธ๋์น๋ฅผ ๋ง๋ ๋ค. ๊ทธ๋ฆฌ๊ณ  ๊ฐ์ ์์์ ์งํํ ํ, ์์์ด ๋๋ ์ฌ๋์ ๋ฉ์ธ ๋ธ๋์น์ ์์ ์ ๋ธ๋์น์ ๋ณ๊ฒฝ ์ฌํญ์ ์ ์ฉํ๋ค.
- git branch [branch name] : branch๋ฅผ ์์ฑํ๋ค.
- git branch checkout/switch [branch name] : ํด๋น branch๋ก ์ด๋ํ๋ค.
- git merge [branch name] : **merge๋ฅผ ํ  branch๋ก ์ด๋ํ ํ ์คํํด์ผ ํ๋ค.** branch์์ ์์ํ ๋ด์ฉ์ main branch์ ํฉ์ณ์ฃผ๋ ๊ธฐ๋ฅ์ด๋ค.
>![](https://images.velog.io/images/songjy377/post/b743a8fa-a2e7-4b0a-adc2-3e4053eb6b89/image.png)
>- ์ด๋ conflict๊ฐ ๋ฐ์ํ  ์ ์๋ค.
>- conflict๋ ํ์ผ์ ํ์ธํด ๋ณด๋ฉด
>
>![](https://images.velog.io/images/songjy377/post/7663b18c-3fd7-4356-8c3b-82a1666f70c8/image.png)
>
>- ์ด๋ฐ ํํ๋ก ๋ํ๋๋๋ฐ, ์ด๋ ์ฝ๋๋ฅผ ์ ์ ํ๊ฒ ์์ ํด์ค ํ ์ ์ฅํด์ฃผ์.
> 
>![](https://images.velog.io/images/songjy377/post/ab6a3aa1-39f6-463e-b73b-ee7860855808/image.png)
>- ์ ์ฅํด ์ค ํ git add -> commit๋ฅผ ํ๋ ค๊ณ  ํ๋ฉด ์๋ ์ฌ์ง์ฒ๋ผ conflict์ ๋์ commit messages๋ค์ด ๋ฏธ๋ฆฌ ์์ฑ๋์ด ์๋ค. ์ฌ๊ธฐ์ message๋ฅผ ๋ํด์ค ํ commit์ ํ๋ฉด ๋๋ค.
>
> ![](https://images.velog.io/images/songjy377/post/d187f2c6-ce8c-44ae-8da1-d887f4c37d76/image.png)
>
> - branch push๋ git push [remote] [branch name] ์ ํํ๋ก ํด์ค๋ค.
> - branch -d [branch name] : branch๋ฅผ ์ญ์ ํ  ์ ์๋ค.
 
## ๐ฅbranching models

- branch๋ฅผ ์์ฑํ๊ณ , ๋ซ๊ณ , ํฉ์น๊ณ  ํ๋ ํ๋๋ก ๋ฌถ์ commands๋ฅผ ์ ์กํ์ฌ ๋ง์ ์์์ ์ค์ฌ์ค๋ค.

- git flow
  - (hotfix)- master -(release)- develop - feature
  - pros: ๊ฐ์ฅ ๋ง์ด ์ ์ฉ, ๊ฐ ๋จ๊ณ๊ฐ ๋ชํํ ๊ตฌ๋ถ
  - cons: ๋ณต์ก..
- github flow
  - master - feature
  - pros: ๋ธ๋์น ๋ชจ๋ธ ๋จ์ํ, master์ ๋ชจ๋  ์ปค๋ฐ์ deployable
  - cons: CI ์์กด์ฑ ๋์. ๋๊ตฌ ํ๋๋ผ๋ ์ค์ํ๋ค๊ฐ..(pull request๋ก ๋ฐฉ์ง)
- gitlab flow
  - production - pre-production - master - feature
  - pros: deploy, issue์ ๋ํ ๋์์ด ๊ฐ๋ฅํ๋๋ก ๋ณด์
  - cons: git flow์ ๋ฐ๋ (master-develop, production-master)
  
## ๐ฅgit flow

![](https://images.velog.io/images/songjy377/post/a2283914-e309-4892-863b-8d45471df2f9/image.png)
  - ์ฐธ๊ณ  ์ฌ์ดํธ [gitflow](https://danielkummer.github.io/git-flow-cheatsheet/index.ko_KR.html)
  
### 1. init
  >
  - ๊ธฐ์กด git ์ ์ฅ์ ๋ด์์ ์ด๊ธฐํํ๋ ๊ฒ์ผ๋ก git-flow์ ์ฌ์ฉ์ ์์ํฉ๋๋ค.
  - ```git flow init```

  ![](https://images.velog.io/images/songjy377/post/3c91ece5-195c-4c2f-b785-1351add69544/image.png)
  
### 2. ๊ธฐ๋ฅ ์์ํ๊ธฐ
  >
  - ์ ๊ธฐ๋ฅ์ ๊ฐ๋ฐ์ 'develop' ๋ธ๋์น์์ ์์ํฉ๋๋ค.
  - ๋ค์๊ณผ ๊ฐ์ด ์ ๊ธฐ๋ฅ์ ๊ฐ๋ฐ์ ์์ํฉ๋๋ค.
  - ```git flow feature start MYFEATURE```
  - ์ด๊ฒ์ 'develop'์ ๊ธฐ๋ฐํ ์ ๊ธฐ๋ฅ(feature) ๋ธ๋์น๋ฅผ ์์ฑํ๊ณ  ๊ทธ ๋ธ๋์น๋ก ์ ํํฉ๋๋ค.

  ![](https://images.velog.io/images/songjy377/post/22953e7c-0845-4b6d-9a59-58eb9db0c572/image.png)
  
### 3. ๊ธฐ๋ฅ ์๋ฃ
  >
  - ```git flow feature finish MYFEATURE```
  - ๋ช๋ น์ด๋ฅผ ์น๊ธฐ ์ ์ **add์ commit์ ์งํ**ํด์ค์ผ ํ๋ค.
  - ๊ธฐ๋ฅ ๊ฐ๋ฐ์ ์๋ฃํฉ๋๋ค. ์ด๊ฒ์ ๋ค์ ์์๋ค์ ์ํํฉ๋๋ค.
  - MYFEATURE ๋ธ๋์น๋ฅผ 'develop'์ ๋ณํฉ(merge)ํฉ๋๋ค.
  - ๊ธฐ๋ฅ ๋ธ๋์น๋ฅผ ์ญ์ ํฉ๋๋ค.
  - 'develop' ๋ธ๋์น๋ก ์ ํํฉ๋๋ค.

  ![](https://images.velog.io/images/songjy377/post/b4d4a24f-350d-4594-b84a-483ad7344eb2/image.png)
  
### 5. Release ์์
> `git flow release start RELEASE [BASE]`
  ![](https://images.velog.io/images/songjy377/post/ea944d37-e720-4785-9790-3e838b377ebd/image.png)
  
### 6. Release ์๋ฃ
  >
  ``` git flow release finish RELEASE ```
  - ๋ค์ ๋จ๊ณ๋ฅผ ์ํํฉ๋๋ค. vi์ฐฝ์ด ๋์ต๋๋ค.
    1. 'release' ๋ธ๋์น๋ฅผ 'master' ๋ธ๋์น์ ๋ณํฉ(merge)
    2. ๋ฆด๋ฆฌ์ค๋ฅผ ๋ฆด๋ฆฌ์ค ์ด๋ฆ์ผ๋ก ํ๊ทธ(tag) **์ด ๋ฒ์ ์ ๋ง๋ค๊ธฐ ์ํด ์ด๋ค ๊ฐ๋ฐ์ ํ๋์ง๋ฅผ ์ ๋ถ ๋ค ์ ์ด์ค์ผ ํฉ๋๋ค.**
    3. ๋ฆด๋ฆฌ์ค๋ฅผ 'develop' ๋ธ๋์น๋ก ์ฌ๋ณํฉ(back-merge)
    4. 'release' ๋ธ๋์น ์ญ์ 
  ![](https://images.velog.io/images/songjy377/post/d774f694-b672-4622-abc9-ca2462358bf3/image.png)
  - ๊ทธ๋ฆฌ๊ณ  develop, main์ ๋ด์ฉ์ origin์ pushํด ์ค๋๋ค. 
  **git push --tags๋ฅผ ์ฌ์ฉํด ํ๊ทธ๋ค์ pushํ๋ ๊ฒ์ ์์ง๋ง์ธ์.**
  