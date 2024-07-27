<template>
  <div>
    <div style="line-height: 50px">
      <el-autocomplete style="width: 600px" :fetch-suggestions="querySearch"
                placeholder="请输入内容" v-model="input" class="input-with-select" clearable>
        <el-select style="width: 110px" v-model="value" slot="prepend" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        <el-button slot="append" icon="el-icon-search" @click="conditionQuery"></el-button>
      </el-autocomplete>

      <!--按钮-->
      <div style="float: left">
        <el-button @click="openAddUserDialog" type="primary" icon="el-icon-plus">新增</el-button>
        <el-button type="danger" icon="el-icon-delete" @click="removeUsers">批量删除</el-button>
      </div>
    </div>
    <el-table
      :data="tableData"
      border
      style="width: 100%;line-height: 40px"
      @selection-change="selectChange"
      :default-sort = "{prop: 'date', order: 'descending'}"
      >
      <!--多选框（批量操作使用）-->
      <el-table-column
        type="selection"
        width="55"
      >
      </el-table-column>

      <el-table-column
        prop="userRname"
        label="姓名"
        sortable
        width="100">
      </el-table-column>
      <el-table-column
        prop="userName"
        label="账号"
        sortable
        width="120">
      </el-table-column>
      <el-table-column
        prop="roleList[0].roleName"
        label="角色"
        sortable
        width="80">
      </el-table-column>
      <el-table-column
        prop="userGender"
        label="性别"
        sortable
        width="50">
      </el-table-column>
      <el-table-column
        prop="userTel"
        label="电话"
        sortable
        width="120">
      </el-table-column>
      <el-table-column
        prop="userEmail"
        label="邮箱"
        sortable
        width="185">
      </el-table-column>
      <el-table-column
        prop="registrationDate"
        label="注册时间"
        sortable
        width="120">
      </el-table-column>
      <el-table-column
        prop="maxNum"
        label="最大借书数"
        sortable
        width="90">
      </el-table-column>
      <el-table-column
        prop="borrowNum"
        label="已借阅书数"
        sortable
        width="90">
      </el-table-column>
      <el-table-column
        prop="userStatus"
        label="账号状态"
        sortable
        width="100">
      </el-table-column>

      <el-table-column
        label="操作"
        width="160">
        <template slot-scope="scope">
          <el-button @click="openUserUpdateDialog(scope.row)" type="primary" size="small" v-show="scope.row.roleName!='admin'">修改</el-button>
          <el-button @click="removeUser(scope.row.userId)" type="danger" v-show="scope.row.roleName!='admin'" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
        :current-page="userQueryParams.currentPage"
        :page-sizes="[5, 7, 10]"
        :page-size="userQueryParams.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totals" class="el-pagination"
        @current-change="currentChange"
        @size-change="sizeChange"
      >
      </el-pagination>
    </div>

    <!--新增模态框-->
    <el-dialog title="用户添加" :visible.sync="isOpenAddDialog" style="line-height: 35px" class="el-dialog__header">
      <el-form :model="addUserInfo" :rules="rules" class="demo-ruleForm" ref="addForm">
        <el-row>
          <el-col :span="12">
            <el-form-item label="账号名称" :label-width="formLabelWidth" prop="userName">
              <el-input v-model="addUserInfo.userName" autocomplete="off" @blur="checkUserName" clearable></el-input>
            </el-form-item>
            <el-form-item label="姓名" :label-width="formLabelWidth" prop="userRname">
              <el-input v-model="addUserInfo.userRname" autocomplete="off" clearable></el-input>
            </el-form-item>
            <el-form-item label="初始密码" :label-width="formLabelWidth" prop="userPassword">
              <el-input v-model="addUserInfo.userPassword"  autocomplete="off" type="password" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="性别" :label-width="formLabelWidth" prop="userGender">
              <el-select v-model="addUserInfo.userGender" placeholder="请选择性别">
                <el-option v-for="item in userGender"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系电话" :label-width="formLabelWidth" prop="userTel">
              <el-input v-model="addUserInfo.userTel" autocomplete="off" clearable></el-input>
            </el-form-item>
            <el-form-item label="电子邮箱" :label-width="formLabelWidth" prop="userEmail">
              <el-input v-model="addUserInfo.userEmail" autocomplete="off" clearable></el-input>
            </el-form-item>
            <el-form-item label="角色" :label-width="formLabelWidth" prop="roleName">
              <el-select v-model="addUserInfo.roleName" placeholder="请选择角色">
                <el-option v-for="item in roles"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeAddUser('addForm')">取 消</el-button>
        <el-button type="primary" @click="addUser('addForm')">确 定</el-button>
      </div>

    </el-dialog>


    <!--修改模态框-->
    <el-dialog title="用户修改" :visible.sync="isOpenUpdateDialog" style="line-height: 35px" class="el-dialog__header">
      <el-form :model="updateUserInfo" :rules="rules" class="demo-ruleForm" ref="updateForm">
        <el-row>
          <el-col :span="12">
            <el-form-item label="姓名" :label-width="formLabelWidth" prop="userRname">
              <el-input v-model="updateUserInfo.userRname" autocomplete="off" clearable></el-input>
            </el-form-item>
            <el-form-item label="性别" :label-width="formLabelWidth" prop="userGender">
              <el-select v-model="updateUserInfo.userGender" placeholder="请选择性别">
                <el-option v-for="item in userGender"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="账号状态" :label-width="formLabelWidth" prop="userStatus">
              <el-select v-model="updateUserInfo.userStatus" placeholder="请选择状态">
                <el-option v-for="item in userStatus"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="最大借书数" :label-width="formLabelWidth" prop="maxNum">
              <el-input v-model="updateUserInfo.maxNum" autocomplete="off" clearable></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="联系电话" :label-width="formLabelWidth" prop="userTel">
              <el-input v-model="updateUserInfo.userTel" autocomplete="off" clearable></el-input>
            </el-form-item>
            <el-form-item label="电子邮箱" :label-width="formLabelWidth" prop="userEmail">
              <el-input v-model="updateUserInfo.userEmail" autocomplete="off" clearable></el-input>
            </el-form-item>
            <el-form-item label="角色" :label-width="formLabelWidth" prop="roleName">
              <el-select v-model="updateUserInfo.roleName" placeholder="请选择角色">
                <el-option v-for="item in roles"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeUpdateUser('updateForm')">取 消</el-button>
        <el-button type="primary" @click="updateUser('updateForm')">确 定</el-button>
      </div>

    </el-dialog>

  </div>

</template>

<script>
  export default {
    name: "UserM",
    data() {
      return {
        value: '',
        input: '',
        selectName:[],
        selectRole:[],
        selectStatus:[],
        options: [{
          value: 'userName',
          label: '账号名称'
        }, {
          value: 'userRname',
          label: '姓名'
        }, {
          value: 'roleName',
          label: '账号角色'
        }, {
          value: 'userStatus',
          label: '账号状态'
        }],
        //表格展示数据
        tableData: [],
        ids: [],
        userGender: [{
          value: '男',
          label: '男'
        }, {
          value: '女',
          label: '女'
        }],
        roles: [{
          value: 'librarian',
          label: 'librarian'
        }, {
          value: 'user',
          label: 'user'
        }],
        userStatus:[{
          value: '正常',
          label: '正常'
        },{
          value: '冻结',
          label: '冻结'
        }],
        //分页参数
        totals: 0,//总条数
        formLabelWidth: '120px',
        isOpenAddDialog: false,
        isOpenUpdateDialog: false,
        userQueryParams: {
          userName: undefined,
          userRname: undefined,
          roleName: undefined,
          userStatus: undefined,
          currentPage: 1,
          pageSize: 5
        },
        addUserInfo: {
          userName: undefined,
          userPassword: "111111",
          userRname: undefined,
          userGender: undefined,
          roleName: undefined,
          userTel: undefined,
          userEmail: undefined
        },
        updateUserInfo: {
          userId: undefined,
          userName: undefined,
          userRname: undefined,
          userGender: undefined,
          roleName: undefined,
          userTel: undefined,
          userEmail: undefined,
          userStatus: undefined,
          maxNum:undefined
        },
        rules: {
          userName: [
            {required: true, message: '请输入账号名称', trigger: 'blur'},
            {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
          ],
          userRname: [
            {required: true, message: '请输入姓名', trigger: 'blur'},
          ],
          roleName: [
            {required: true, message: '请选择角色', trigger: 'change'},
          ]
        }
      }
    },
    methods:{
        querySearch(queryString, cb) {
          const _self = this
          if (_self.value == 'roleName') {
            var select = _self.selectRole
          }
          else if (_self.value == 'userStatus') {
            var select = _self.selectStatus
          }
          else var select = _self.selectName
          var results = queryString ? select.filter(_self.createFilter(queryString)) : select;
          // 调用 callback 返回建议列表的数据
          cb(results);
        },
        createFilter(queryString) {
          return (select) => {
            return (select.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
          };
        },
        loadAllName() {
          return []
        },
        loadAllRole() {
          return [{
            "value": "admin"
          }, {
            "value": "librarian"
          },{
            "value": "user"
          }]
        },
      loadAllStatus() {
        return [{
          "value": "正常"
        }, {
          "value": "冻结"
        }]
      },
        queryUserInfo(){
          const _self = this
          _self.$http.get("/api/user",{
            params: _self.userQueryParams
          }).then(function (resp) {
              console.log(resp)
            if(resp.data.code==200){
              _self.tableData = resp.data.data.userInfos
              _self.totals = resp.data.data.totals
            }
            if(_self.totals < _self.userQueryParams.pageSize) {
              for (let i = 0; i < _self.totals; i++) {
                if (_self.tableData[i].userStatus == 1) _self.tableData[i].userStatus = "正常"
                else _self.tableData[i].userStatus = "冻结"
              }
            }
            else {
              for (let i = 0; i < _self.userQueryParams.pageSize; i++) {
                if (_self.tableData[i].userStatus == 1) _self.tableData[i].userStatus = "正常"
                else _self.tableData[i].userStatus = "冻结"
              }
            }
          })
        },
        currentChange(cpage){
          const _self = this
          _self.userQueryParams.currentPage = cpage
          _self.queryUserInfo()
        },
        sizeChange(psize){
          const _self = this
          _self.userQueryParams.pageSize = psize
          _self.userQueryParams.currentPage = 1
          _self.queryUserInfo()
        },
        conditionQuery(){
          const _self = this
          _self.userQueryParams.currentPage = 1
          _self.userQueryParams.userName = undefined
          _self.userQueryParams.userRname = undefined
          _self.userQueryParams.roleName = undefined
          _self.userQueryParams.userStatus = undefined
          if(_self.value == 'userName') _self.userQueryParams.userName = _self.input
          else if(_self.value == 'userRname') _self.userQueryParams.userRname = _self.input
          else if(_self.value == 'roleName') _self.userQueryParams.roleName = _self.input
          else if(_self.value == 'userStatus') {
            if (_self.input == '正常') _self.userQueryParams.userStatus = 1
            else if(_self.input == '冻结') _self.userQueryParams.userStatus = 2
          }
          console.log(_self.userQueryParams.roleName)
          _self.queryUserInfo()
        },
      removeUser(userid){
          const _self = this
        _self.$confirm('此操作将永久删除该文件, 是否继续?', '提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          _self.$http.delete("/api/user/"+userid).then(resp=>{
            if(resp.data.code==200){
              _self.userQueryParams.currentPage = 1
              _self.queryUserInfo()
            }
          })
        })
      },

      selectChange(selection){
          // console.log(selection)
        const _self = this
        _self.ids = []
        for(let i = 0;i<selection.length;i++){
          if(selection[i].userName!="admin"){
            _self.ids[i] = selection[i].userId
          }
        }
      },
      removeUsers(){
        const _self = this
          if(_self.ids.length==0){
            _self.$message.warning("请选中要删除的数据！")
          }else {
            _self.$confirm("确认是否删除选中的数据？","提示",{
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(function () {
                _self.$http.delete("/api/users/"+_self.ids).then(resp=>{
                  if(resp.data.code==200){
                    _self.$message.success("删除成功！")
                    _self.userQueryParams.currentPage = 1
                    _self.queryUserInfo()
                  }
                })
            })
          }
      },
      openAddUserDialog(){
        const _self = this
        _self.isOpenAddDialog = true
      },

      checkUserName(){
        const _self = this
        _self.$http.get("/api/user/"+_self.addUserInfo.userName).then(resp=>{
          if(resp.data.code==402){
            _self.$message.error(resp.data.msg)
          }
        })
      },
      addUser(addForm){
        const _self = this
        _self.$refs[addForm].validate((valid) => {
          if (valid) {
            _self.$http.post("/api/user",_self.addUserInfo).then(resp=>{
              if(resp.data.code==200){
                _self.$message.success(resp.data.msg)
                _self.isOpenAddDialog = false
                _self.$refs[addForm].resetFields();
                _self.userQueryParams.currentPage = 1
                _self.queryUserInfo()
              }
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        })
      },
      closeAddUser(addForm){
          const _self = this
        _self.isOpenAddDialog = false;
        _self.$refs[addForm].resetFields();
      },
      openUserUpdateDialog(userInfo){
          const _self  = this
        _self.isOpenUpdateDialog = true
        _self.updateUserInfo.userId = userInfo.userId
        _self.updateUserInfo.userName = userInfo.userName
        _self.updateUserInfo.userRname = userInfo.userRname
        _self.updateUserInfo.userGender = userInfo.userGender
        _self.updateUserInfo.userTel = userInfo.userTel
        _self.updateUserInfo.userEmail = userInfo.userEmail
        _self.updateUserInfo.roleName = userInfo.roleList[0].roleName
        _self.updateUserInfo.userStatus = userInfo.userStatus
        _self.updateUserInfo.maxNum = userInfo.maxNum
      },
      checkUpdateUserName(){
        const _self = this
        _self.$http.get("/api/user/"+_self.updateUserInfo.userName).then(resp=>{
          if(resp.data.code==402){
            _self.$message.error(resp.data.msg)
          }
        })
      },
      updateUser(updateForm){
        const _self  = this
        _self.$refs[updateForm].validate((valid) => {
          if (valid) {
            _self.$http.put("/api/user",_self.updateUserInfo).then(resp=>{
              if(resp.data.code==200){
                _self.$message.success(resp.data.msg)
                _self.isOpenUpdateDialog = false
                _self.queryUserInfo()
              }
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        })
      },
      closeUpdateUser(updateForm){
          const _self = this
        _self.isOpenUpdateDialog = false;
        _self.$refs[updateForm].resetFields();
      }
    },
    //页面渲染完毕执行
    mounted() {
      this.queryUserInfo()
      this.selectRole = this.loadAllRole();
      this.selectStatus = this.loadAllStatus();
      this.selectName = this.loadAllName()
    }
  }
</script>

<style scoped>
  .el-select .el-input {
    width: 130px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }
  .el-pagination{
    float: right;
  }
  .el-dialog__header{
    text-align: left;
  }
</style>
