## ๐README.md ๋?
- ํ๋ก์ ํธ์ Repository๋ฅผ ์ค๋ชํ๋ ์ฑ์ ํ์ง์ ๊ฐ์ ๋ฌธ์
- ๋์ ๋๋ฃ, ์ด repo์ ์ฌ์ฉ์๋ฅผ ์ํ ๋ฌธ์
```
  # Project Name
  Abstract your project in few lines.
  see [project sample page](project link)

  ## Documentation

  ### Installation
  To install,
  `$ pip install sesame`
  and run `$ python open_sesame.py`

  ### Supported Python versions
  `>=3.6`

  ### More Information
  - [API docs]()
  - [Official website]()

  ### Contributing
  Please see [CONTRIBUTING.md]()

  ### License
  Sesame is Free software, and may be redistributed under the terms of specified in the [LICENSE]() file.
```
## โ.gitignore ๋?
- .gitignore๋ git์ด ํ์ผ์ ์ถ์ ํ  ๋, ์ด๋ค ํ์ผ์ด๋ ํด๋ ๋ฑ์ ์ถ์ ํ์ง ์๋๋ก ๋ช์ํ๊ธฐ ์ํด ์์ฑํ๋ฉฐ, ํด๋น ๋ฌธ์์ ์์ฑ๋ ๋ฆฌ์คํธ๋ ์์ ์ฌํญ์ด ๋ฐ์ํด๋ git์ด ๋ฌด์ํ๊ฒ ๋๋ค. 
- ํน์  ํ์ผ ํ์ฅ์๋ฅผ ๋ฌด์ํ๊ฑฐ๋ ์ด๋ฆ์ ํจํด์ด ์กด์ฌํ๋ ๊ฒฝ์ฐ, ๋๋ ํน์  ๋๋ ํ ๋ฆฌ ์๋์ ๋ชจ๋  ํ์ผ์ ๋ฌด์ํ  ์ ์๋ค. node_modules/๋ฅผ ์ถ๊ฐํ๋ฉด node_modules/ํ์ ๋ชจ๋  ํ์ผ/ํด๋๋ค์ด ๋ฌด์๋๋ค.

_gitignore ํ์ผ ์๋์์ฑ ์ฌ์ดํธ - https://www.toptal.com/developers/gitignore_
```
    .hidden/**
    node_modules/
    keyfile.pem
    rootkey.*
    *.java
```

## ๐ LICENSE
- ์คํ์์ค ํ๋ก์ ํธ์์ ๊ฐ์ฅ ์ค์ํ License๋ ๋ด๊ฐ ๋ง๋ค ๋์๋, ๋ฐฐํฌํ  ๋์๋ ๊ฐ์ฅ ์ ๊ฒฝ์จ์ผ ํ๋ค!!

- ๊ฐ์ฅ ๋ง์ด ์ฌ์ฉํ๋ License ๋ชฉ๋ก
>
  - MIT License - MIT์์ ๋ง๋  ๋ผ์ด์ผ์ค๋ก, ๋ชจ๋  ํ๋์ ์ ์ฝ์ด ์์ผ๋ฉฐ, ์ ์๊ถ์๋ ์ํํธ์จ์ด์ ๊ด๋ จํ ์ฑ์์์ ์์ ๋กญ์ต๋๋ค.
  - Apache License 2.0 - Apache ์ฌ๋จ์ด ๋ง๋  ๋ผ์ด์ผ์ค๋ก, ํนํ๊ถ ๊ด๋ จ ๋ด์ฉ์ด ํฌํจ๋์ด ์์ต๋๋ค.
  - GNU General Public License v3.0 - ๊ฐ์ฅ ๋ง์ด ์๋ ค์ ธ์์ผ๋ฉฐ, ์๋ฌด์ฌํญ(ํด๋น ๋ผ์ด์ผ์ค๊ฐ ์ ์ฉ๋ ์์ค์ฝ๋ ์ฌ์ฉ์ GPL์ ๋ฐ๋ผ์ผ ํจ)์ด ์กด์ฌํฉ๋๋ค.
