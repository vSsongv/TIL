## 📃README.md 란?
- 프로젝트와 Repository를 설명하는 책의 표지와 같은 문서
- 나와 동료, 이 repo의 사용자를 위한 문서
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
## ❌.gitignore 란?
- .gitignore는 git이 파일을 추적할 때, 어떤 파일이나 폴더 등을 추적하지 않도록 명시하기 위해 작성하며, 해당 문서에 작성된 리스트는 수정사항이 발생해도 git이 무시하게 된다. 
- 특정 파일 확장자를 무시하거나 이름에 패턴이 존재하는 경우, 또는 특정 디렉토리 아래의 모든 파일을 무시할 수 있다. node_modules/를 추가하면 node_modules/하위 모든 파일/폴더들이 무시된다.

_gitignore 파일 자동생성 사이트 - https://www.toptal.com/developers/gitignore_
```
    .hidden/**
    node_modules/
    keyfile.pem
    rootkey.*
    *.java
```

## 💠LICENSE
- 오픈소스 프로젝트에서 가장 중요한 License는 내가 만들 때에도, 배포할 때에도 가장 신경써야 한다!!

- 가장 많이 사용하는 License 목록
>
  - MIT License - MIT에서 만든 라이센스로, 모든 행동에 제약이 없으며, 저작권자는 소프트웨어와 관련한 책임에서 자유롭습니다.
  - Apache License 2.0 - Apache 재단이 만든 라이센스로, 특허권 관련 내용이 포함되어 있습니다.
  - GNU General Public License v3.0 - 가장 많이 알려져있으며, 의무사항(해당 라이센스가 적용된 소스코드 사용시 GPL을 따라야 함)이 존재합니다.
