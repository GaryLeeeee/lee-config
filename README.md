# 配置服务
## 一、背景
为了方便维护，提供配置相关的sdk，并提供管理后台等

## 二、环境配置
### 1.管理后台
* [创建一个vue项目](https://garyleeeee.github.io/2022/03/02/Vue%E9%A1%B9%E7%9B%AE%E5%88%9B%E5%BB%BA%E6%96%B9%E5%BC%8F/)

* 安装iview UI组件库

`npm install view-design --save`

* 安装axios(网络请求库)

  * 安装指令：npm install axios
  * 文档链接：http://axios-js.com/zh-cn/docs/index.html

* 引用指引(以iview和axios为例)
```
import axios from 'axios';
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
import Vue from "vue";

Vue.use(ViewUI);
```
