---
title: "신규 글 테스트"
date: "2025-08-19T10:00:00+16:00"
draft: false            # 작성 중엔 true, 발행 전엔 false 로!
author: ["이경민"]  # 여러 명이면 ["작성자1","작성자2"]
tags: ["Study"]  # 자유롭게
categories: ["Devlog"]
description: "다른 환경에서 글 작성 방법 "
ShowToc: false
TocOpen: false
cover:
  image: ""            # 같은 폴더에 이미지 두고 파일명만 적기
  alt: ""
  relative: true
---

<!--more-->

## 글 작성 방법

만약 로컬 환경에 hugo가 설치 되어 있다면

1. clone한 로컬 경로로 이동
2. hugo new --kind post posts/파일명/index.md 로 파일 생성 
3. index.md에 형식에 맞게 수정하면서 공부한거 정리 

글 다 작성 한 후 git 명령어로
1. git fetch origin
2. git checkout -b post/my-article <- 이름 변경해도 됨> 
3. content/post/에 파일 수정하고 추가

- git add content 
- git commit -m "Post: 파일명 by <작성자ID>"
- git push origin post/my-article


