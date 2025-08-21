---
title: "Git.io 글 작성 방법"
date: "2025-08-21T10:00:00+09:00"
draft: false              
author: ["이경민"]     
tags: ["Study"]     # 자유롭게
categories: ["Devlog"]
description: "Git.io 글 작성 방법"
ShowToc: false
TocOpen: false
cover:
  image: ""               # 같은 폴더에 이미지 두고 파일명만 적기(예: cover.jpg)
  alt: ""
  relative: true
---
<!--more-->
## 글 작성 방법

### 로컬 환경에 hugo가 설치 되어 있지 않은 경우 (처음 1회만)
1. winget이 있는지 확인 (없는 경우 Microsoft Store에서 "App Installer"설치 후 다시 실행)
- winget -v
2. Hugo Extended 설치
- winget install --id Hugo.Hugo.Extended -e
3. 설치 확인
- hugo version

### Git clone 후 최신화 및 Branch 생성
1. 원격 레포를 로컬 공간에 클론 (처음 1회만)
- git clone https://github.com/LKMGIT/LKMGIT.github.io.git
![git clone 결과 화면](git.png)  
2. 작업 공간 최신화 확인
- git fetch origin
- git rebase origin/main (fetch 했는데 로컬과 다른 파일이 있다면 진행)
- git pull ( 위에 rebase 가 안되면 해보기)
- git merge origin/main (위에도 안되면 해보기)
3. 할당 해준 본인 브랜치로 이동 
- git switch (본인 할당 브랜치) ex: git switch post/LKM

### 글 작성 하기 
1. index.md 파일 생성하기
- hugo new posts/파일명/index.md (폴더 위치는 C:\Users\LKM\Git.io\LKMGIT.github.io\content\posts 안에 있음)
![index.md위치](local.png)
2. 파일 형식 보면서 글 쓰기 (글 구성은 수정가능)

### 글 작성 후 PR
*** 반드시 PR을 하기 전 fetch를 진행 *** (방금 본인이 만든거 제외 현재 깃허브 웹에 올라온 파일이랑 본인 로컬 파일이랑 같은지 확인 하고 올리셔야합니다.)
1. 로컬에서 미리보기 
- hugo server -D (로컬에서 서버 열어서 자기가 쓴 글 확인하고 이상한 부분 있으면 수정 로컬 켜놓고 작업하면 바로바로 보기 좋습니다)
![hugo서버열기](hugoserver.png)
2. 로컬에서 확인이 끝나고 PR 하는 방법 
- git add .
- git commit -m "Post: 파일명 by <작성자 ID>"
- git push -u origin post/파일명