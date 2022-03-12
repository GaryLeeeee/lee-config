<template>
  <div id="app">
    <!--    <img src="../assets/logo.png">-->
    <router-view/>
    <Row>
      <Table
        :loading="leeObjectConfig.loading"
        border
        :columns="leeObjectConfig.columns"
        :data="leeObjectConfig.data"
        ref="table"
        sortable="custom"
      ></Table>
    </Row>

    <!--    <Row type="flex" justify="end" class="page">-->
    <!--      <Page-->
    <!--        :current="leeObjectConfig.pageNum"-->
    <!--        :total="leeObjectConfig.total"-->
    <!--        :page-size="leeObjectConfig.pageSize"-->
    <!--        @on-change="changePageNum"-->
    <!--        @on-page-size-change="changePageSize"-->
    <!--        :page-size-opts="[10,20,50]"-->
    <!--        size="small"-->
    <!--        show-total-->
    <!--        show-elevator-->
    <!--        show-sizer-->
    <!--      ></Page>-->
    <!--    </Row>-->
  </div>
</template>

<!--<script src="https://unpkg.com/axios/dist/axios.min.js"></script>-->
<script>
import axios from 'axios';
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
import Vue from "vue";

Vue.use(ViewUI);

export default {
  name: 'lee-config-web-admin',
  //组件
  comments: {},
  //数据
  data() {
    return {
      leeObjectConfig: {
        //列名
        columns: [
          {
            title: 'id',
            key: 'id'
          },
          {
            title: '配置-键',
            key: 'key'
          },
          {
            title: '配置-值',
            key: 'value'
          },
          {
            title: '描述',
            key: 'description'
          },
          {
            title: '版本(每次更新+1)',
            key: 'version'
          },
          {
            title: '使用频率(每次使用+1)',
            key: 'frequency'
          },
          {
            title: '操作者uid',
            key: 'operatorUid'
          },
          {
            title: '操作者名称',
            key: 'operatorName'
          }
        ],
        //数据
        data: [],
        total: 0,
        pageNum: 1,
        pageSize: 10,
        loading: false,
        //搜索表单
        searchForm: {}
      },
      leeObjectConfigHistory: {}
    }
  },
  //函数
  methods: {
    init() {
      this.queryAllConfigs();
    },
    changePageNum() {

    },
    changePageSize() {

    },
    queryAllConfigsByPage() {

    },
    queryAllConfigs() {
      axios.get("http://localhost:8080/admin/lee-config/queryAllConfigs")
        .then(
          res => {
            if (res.data.code === 0) {
              this.leeObjectConfig.data = res.data.data;
            } else {
              alert("查询配置失败!");
            }
          }
        )
    },
    addConfig() {

    },
    updateConfig() {

    },
    queryConfigHistories() {

    },
    rollback() {

    }
  },
  //挂载(启动调用)
  mounted() {
    this.init();
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
