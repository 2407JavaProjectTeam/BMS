<template>
  <div>
    <div style="line-height: 40px">
      <el-form :inline="true"  class="demo-form-inline">
        <el-form-item label="姓名">
          <el-input v-model="userQueryParams.userRname"  placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="userQueryParams.userTel"  placeholder="电话"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="conditionQuery">查询</el-button>
        </el-form-item>
      </el-form>


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
      >
      <!--多选框（批量操作使用）-->
      <el-table-column
        type="selection"
        width="55"
      >
      </el-table-column>

      <el-table-column
        prop="userHiredate"
        label="入职日期"
        width="120">
      </el-table-column>
      <el-table-column
        prop="userRname"
        label="姓名"
        width="120">
      </el-table-column>
      <el-table-column
        prop="userName"
        label="账号"
        width="100">
      </el-table-column>
      <el-table-column
        prop="userTel"
        label="电话"
        width="120">
      </el-table-column>
      <el-table-column
        prop="userGender"
        label="性别"
        width="50">
      </el-table-column>
      <el-table-column
        prop="deptName"
        label="部门"
        width="80">
      </el-table-column>
      <el-table-column
        prop="userEmail"
        label="邮箱"
        width="150">
      </el-table-column>
      <el-table-column
        prop="userCode"
        label="员工编号"
        width="100">
      </el-table-column>
      <el-table-column
        prop="userDegree"
        label="学历"
        width="100">
      </el-table-column>

      <el-table-column
        label="操作"
        width="160">
        <template slot-scope="scope">
          <el-button @click="openUserUpdateDialog(scope.row)" type="primary" size="small" v-show="scope.row.userName!='admin'">修改</el-button>
          <el-button @click="removeUser(scope.row.userId)" type="danger" v-show="scope.row.userName!='admin'" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
        :current-page="userQueryParams.currentPage"
        :page-sizes="[2, 4, 5]"
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
      <el-form :model="addUserInfo" class="demo-ruleForm" ref="addForm">
        <el-row>
          <el-col :span="12">
            <el-form-item label="用户名" :label-width="formLabelWidth" prop="userName">
              <el-input v-model="addUserInfo.userName" autocomplete="off" @blur="checkUserName"></el-input>
            </el-form-item>
            <el-form-item label="初始密码" :label-width="formLabelWidth" prop="userPassword">
              <el-input v-model="addUserInfo.userPassword"  autocomplete="off" type="password" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="部门" :label-width="formLabelWidth" prop="deptId">
              <el-select v-model="addUserInfo.deptId" placeholder="请选择部门">
                <!--                <el-option label="总裁办" value="总裁办"></el-option>-->
                <!--                <el-option label="人事部" value="人事部"></el-option>-->
                <el-option v-for="item in depts"
                           :key="item.deptId"
                           :label="item.deptName"
                           :value="item.deptId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="角色" :label-width="formLabelWidth" prop="roleId">
              <el-select v-model="addUserInfo.roleId" placeholder="请选择角色">
                <el-option v-for="item in roles"
                           :key="item.roleId"
                           :label="item.roleName"
                           :value="item.roleId">
                </el-option>
              </el-select>
            </el-form-item>

          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="isOpenAddDialog = false">取 消</el-button>
        <el-button type="primary" @click="addUser('addForm')">确 定</el-button>
      </div>

    </el-dialog>


    <!--修改模态框-->
    <el-dialog title="用户修改" :visible.sync="isOpenUpdateDialog" style="line-height: 35px" class="el-dialog__header">
      <el-form :model="updateUserInfo" class="demo-ruleForm" ref="updateForm">
        <el-row>
          <el-col :span="12">
            <el-form-item label="用户名" :label-width="formLabelWidth" prop="userName">
              <el-input v-model="updateUserInfo.userName" autocomplete="off" @blur="checkUserName"></el-input>
            </el-form-item>

            <el-form-item label="部门" :label-width="formLabelWidth" prop="deptId">
              <el-select v-model="updateUserInfo.deptId" placeholder="请选择部门">
                <!--                <el-option label="总裁办" value="总裁办"></el-option>-->
                <!--                <el-option label="人事部" value="人事部"></el-option>-->
                <el-option v-for="item in depts"
                           :key="item.deptId"
                           :label="item.deptName"
                           :value="item.deptId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="角色" :label-width="formLabelWidth" prop="roleId">
              <el-select v-model="updateUserInfo.roleId"   placeholder="请选择角色">
                <el-option v-for="item in roles"
                           :key="item.roleId"
                           :label="item.roleName"
                           :value="item.roleId">
                </el-option>
              </el-select>
            </el-form-item>

          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="isOpenUpdateDialog= false">取 消</el-button>
        <el-button type="primary" @click="updateUser()">确 定</el-button>
      </div>

    </el-dialog>

  </div>

</template>

<script>
  export default {
    name: "User",
    data(){
      return{
        value:"",
        //表格展示数据
        tableData: [],
        ids:[],
        roles:[],
        depts:[],
        //分页参数
        totals:0,//总条数
        formLabelWidth: '120px',
        isOpenAddDialog: false,
        isOpenUpdateDialog: false,
        userQueryParams:{
          userRname: undefined,
          userTel: undefined,
          currentPage:1,
          pageSize:2
        },
        addUserInfo:{
          userName:undefined,
          userPassword:"111111",
          deptId:undefined,
          roleId: undefined
        },
        updateUserInfo:{
          userId:undefined,
          userName:undefined,
          deptId:undefined,
          roleId: undefined
        }
      }
    },
    methods:{
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

      queryRolesInfo(){
          const _self = this
        _self.$http.get("/api/roles").then(resp=>{
          if(resp.data.code==200){
            _self.roles = resp.data.data
          }
        })
      },

      queryDeptInfo(){
        const _self = this
         _self.$http.get("/api/depts").then(resp=>{
          if(resp.data.code==200){
            _self.depts = resp.data.data
          }
        })
      },
      checkUserName(){
          //将输入的同户名传递到后端，去数据库中匹配，看是否已存在
        const _self = this
        _self.$http.get("/api/user/"+_self.addUserInfo.userName).then(resp=>{
          if(resp.data.code==402){
            _self.$message.error(resp.data.msg)
          }
        })
      },
      addUser(addForm){
        const _self = this
        _self.$http.post("/api/user",_self.addUserInfo).then(resp=>{
          if(resp.data.code==200){
            _self.$message.success(resp.data.msg)
            _self.isOpenAddDialog = false
            _self.$refs[addForm].resetFields();
            _self.userQueryParams.currentPage = 1
            _self.queryUserInfo()
          }
        })
      },
      openUserUpdateDialog(userInfo){
          const _self  = this
        _self.isOpenUpdateDialog = true
        console.log(userInfo)
        _self.updateUserInfo.userId = userInfo.userId
        _self.updateUserInfo.userName = userInfo.userName
        _self.updateUserInfo.deptId = userInfo.deptId
        _self.updateUserInfo.roleId = userInfo.roleList[0].roleId
      },

      updateUser(){
        const _self  = this
        _self.$http.put("/api/user",_self.updateUserInfo).then(resp=>{
            if(resp.data.code==200){
              _self.$message.success(resp.data.msg)
              _self.isOpenUpdateDialog = false
              _self.queryUserInfo()
            }
        })
      }

    },
    //页面渲染完毕执行
    mounted() {
      this.queryUserInfo()
      this.queryRolesInfo()
      this.queryDeptInfo()
    }
  }
</script>

<style scoped>
  .el-pagination{
    float: right;
  }
  .el-dialog__header{
    text-align: left;
  }
</style>
