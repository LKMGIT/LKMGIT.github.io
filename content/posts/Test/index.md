---
title: "글 작성 방법"
date: "2025-08-19T10:00:00+09:00"
draft: true            # 작성 중엔 true, 발행 전엔 false 로!
authors: ["이경민"]  # 여러 명이면 ["작성자1","작성자2"]
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

hugo new --kind post posts/파일명/index.md 로 생성 후
index.md 수정 

- git add content 
- git commit -m "Post: 파일명 by <작성자ID>"
- git push origin post/파일명
 

## 참고 링크
https://lkmgit.github.io/ 
