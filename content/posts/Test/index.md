---
title: "글 작성 방법"
date: "2025-08-19T10:00:00+09:00"
draft: false            # 작성 중엔 true, 발행 전엔 false 로!
author: ["이경민"]  # 여러 명이면 ["작성자1","작성자2"]
tags: ["Study"]  # 자유롭게
categories: ["Devlog"]
description: "git.io 글 작성 방법"
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
- git add content 
- git commit -m "Post: 파일명 by <작성자ID>"
- git push origin post/파일명

로컬 환경에 hugo가 설치 되어 있지 않아 hugo 명령어가 안되면 
content/posts 안에 폴더를 만들고 그 안에 index.md 파일 생성하고 작성


