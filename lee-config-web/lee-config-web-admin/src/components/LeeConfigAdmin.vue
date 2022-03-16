<template>
  <div id="app">
    <!--    <img src="../assets/logo.png">-->
    <router-view/>
    <Row>
      <Button @click="openAddOrUpdateConfigModal" type="primary" icon="md-add">新增配置</Button>
      <Button @click="init" icon="md-refresh">刷新</Button>
    </Row>
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

    <Modal v-model="leeObjectConfig.addOrUpdateFormVisible" :mask-closable="true">
      <Form ref="addOrUpdateForm" :model="leeObjectConfig.addOrUpdateForm">
        <FormItem label="id" prop="id">
          <Input v-model="leeObjectConfig.addOrUpdateForm.id" disabled/>
        </FormItem>
        <FormItem label="键" prop="key">
          <Input v-model="leeObjectConfig.addOrUpdateForm.key"/>
        </FormItem>
        <FormItem label="值" prop="value">
          <Input v-model="leeObjectConfig.addOrUpdateForm.value"/>
        </FormItem>
        <FormItem label="描述" prop="description">
          <Input v-model="leeObjectConfig.addOrUpdateForm.description"/>
        </FormItem>
        <FormItem label="版本" prop="version">
          <Input v-model="leeObjectConfig.addOrUpdateForm.version" disabled/>
        </FormItem>
        <FormItem label="频率" prop="frequency">
          <Input v-model="leeObjectConfig.addOrUpdateForm.frequency" disabled/>
        </FormItem>
        <FormItem label="操作者uid" prop="operatorUid">
          <Input v-model="leeObjectConfig.addOrUpdateForm.operatorUid"/>
        </FormItem>
        <FormItem label="操作者昵称" prop="operatorName">
          <Input v-model="leeObjectConfig.addOrUpdateForm.operatorName"/>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="text" @click="closeAddOrUpdateConfigModal">取消</Button>
        <Button type="primary" :loading="leeObjectConfig.addOrUpdateFormLoading" @click="addOrUpdateConfig">提交</Button>
      </div>
    </Modal>

    <Modal v-model="leeObjectConfigHistory.visible" :mask-closable="false" width="1000px">
      <Row>
        <Table
          :loading="leeObjectConfigHistory.loading"
          border
          :columns="leeObjectConfigHistory.columns"
          :data="leeObjectConfigHistory.data"
          ref="table"
          sortable="custom"
        ></Table>
      </Row>
    </Modal>
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
            key: 'id',
            align: 'center'
          },
          {
            title: '配置-键',
            key: 'key',
            align: 'center'
          },
          {
            title: '配置-值',
            key: 'value',
            align: 'center'
          },
          {
            title: '描述',
            key: 'description',
            align: 'center'
          },
          {
            title: '版本(每次更新+1)',
            key: 'version',
            align: 'center'
          },
          {
            title: '使用频率(每次使用+1)',
            key: 'frequency',
            align: 'center'
          },
          {
            title: '操作者uid',
            key: 'operatorUid',
            align: 'center'
          },
          {
            title: '操作者名称',
            key: 'operatorName',
            align: 'center'
          },
          {
            title: '操作',
            key: 'action',
            align: 'center',
            // width: 200,
            render: (h, params) => {
              return h("div", [
                h('Button', {
                  props: {
                    type: 'primary',
                    size: 'small'
                  },
                  on: {
                    click: () => {
                      this.openConfigHistoryModal(params.row.key);
                    }
                  }
                }, '查看历史记录'),
                h('Button', {
                  props: {
                    type: 'primary',
                    size: 'small'
                  },
                  on: {
                    click: () => {
                      this.openAddOrUpdateConfigModal(params.row);
                    }
                  }
                }, '更新'),
                h('Button', {
                  props: {
                    type: 'error',
                    size: 'small'
                  },
                  on: {
                    click: () => {
                      this.delConfig();
                    }
                  }
                }, '删除')
              ])
            }
          }
        ],
        //数据
        data: [],
        total: 0,
        pageNum: 1,
        pageSize: 10,
        loading: false,
        //搜索表单
        searchForm: {},
        //新增/更新表单
        addOrUpdateFormVisible: false,
        addOrUpdateFormLoading: false,
        addOrUpdateForm: {
          id: "",
          key: "",
          value: "",
          description: "",
          version: 1,
          frequency: 0,
          operatorUid: 0,
          operatorName: "admin"
        }
      },
      leeObjectConfigHistory: {
        //列名
        columns: [
          {
            title: 'id',
            key: 'id',
            align: 'center'
          },
          {
            title: '配置-键',
            key: 'key',
            align: 'center'
          },
          {
            title: '配置-值',
            key: 'value',
            align: 'center'
          },
          {
            title: '描述',
            key: 'description',
            align: 'center'
          },
          {
            title: '版本(每次更新+1)',
            key: 'version',
            align: 'center'
          },
          {
            title: '使用频率(每次使用+1)',
            key: 'frequency',
            align: 'center'
          },
          {
            title: '操作者uid',
            key: 'operatorUid',
            align: 'center'
          },
          {
            title: '操作者名称',
            key: 'operatorName',
            align: 'center'
          },
          {
            title: '操作',
            key: 'action',
            align: 'center',
            // width: 200,
            render: (h, params) => {
              return h("div", [
                h('Button', {
                  props: {
                    type: 'primary',
                    size: 'small'
                  },
                  on: {
                    click: () => {
                      this.rollback(params.row);
                    }
                  }
                }, '回滚')
              ])
            }
          }
        ],
        //数据
        data: [],
        total: 0,
        pageNum: 1,
        pageSize: 10,
        loading: false,
        visible: false
      }
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
    openConfigHistoryModal(key) {
      this.queryConfigHistories(key);

      this.leeObjectConfigHistory.visible = true;
    },
    closeConfigHistoryModal() {
      this.leeObjectConfigHistory.visible = false;
    },
    openAddOrUpdateConfigModal(row) {
      this.leeObjectConfig.addOrUpdateForm.id = row.id;
      this.leeObjectConfig.addOrUpdateForm.key = row.key;
      this.leeObjectConfig.addOrUpdateForm.value = row.value;
      this.leeObjectConfig.addOrUpdateForm.description = row.description;
      this.leeObjectConfig.addOrUpdateForm.version = row.version;
      this.leeObjectConfig.addOrUpdateForm.frequency = row.frequency;
      this.leeObjectConfig.addOrUpdateForm.operatorUid = row.operatorUid;
      this.leeObjectConfig.addOrUpdateForm.operatorName = row.operatorName;

      this.leeObjectConfig.addOrUpdateFormVisible = true;
    },
    closeAddOrUpdateConfigModal() {
      this.leeObjectConfig.addOrUpdateFormVisible = false;
    },
    addOrUpdateConfig() {
      axios.post("http://localhost:8080/admin/lee-config/addOrUpdateConfig", {
        id: this.leeObjectConfig.addOrUpdateForm.id,
        key: this.leeObjectConfig.addOrUpdateForm.key,
        value: this.leeObjectConfig.addOrUpdateForm.value,
        description: this.leeObjectConfig.addOrUpdateForm.description,
        version: this.leeObjectConfig.addOrUpdateForm.version,
        frequency: this.leeObjectConfig.addOrUpdateForm.frequency,
        operatorUid: this.leeObjectConfig.addOrUpdateForm.operatorUid,
        operatorName: this.leeObjectConfig.addOrUpdateForm.operatorName
      }).then(
        res => {
          if (res.data.code === 0) {
            alert("添加/更新配置成功");
            this.leeObjectConfig.addOrUpdateFormVisible = false;
            this.init();
          } else {
            alert("添加/更新配置失败");
          }
        }
      )
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
      axios.post("http://localhost:8080/admin/lee-config/addConfig", {
        params: this.leeObjectConfig.addOrUpdateForm
      }).then(
        res => {
          if (res.data.code === 0) {
            alert("添加配置成功");
          } else {
            alert("添加配置失败");
          }
        }
      )
    },
    updateConfig() {
      axios.post("http://localhost:8080/admin/lee-config/updateConfig", {
        params: this.leeObjectConfig.addOrUpdateForm
      }).then(
        res => {
          if (res.data.code === 0) {
            alert("更新配置成功");
          } else {
            alert("更新配置失败");
          }
        }
      )
    },
    queryConfigHistories(key) {
      axios.get("http://localhost:8080/admin/lee-config/queryConfigHistories", {
        params: {
          key: key
        }
      }).then(
        res => {
          if (res.data.code === 0) {
            this.leeObjectConfigHistory.data = res.data.data.records;
          } else {
            alert("查询配置失败!");
          }
        }
      )
    },
    delConfig() {

    },
    rollback() {
      alert("待实现");
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
