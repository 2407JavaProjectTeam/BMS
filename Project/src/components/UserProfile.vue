<template>
    <div class="app-container">
      <el-row :gutter="20">
        <el-col :span="6" :xs="24">
          <el-card class="box-card">
            <div class="user-info text item">
              {{userInfo}}
            </div>
            <el-divider class="divider-height"></el-divider>
            <div class="text item user-img">
<!--              <el-avatar shape="circle" :size="150" :src="url"></el-avatar>-->
              <div>
                <el-upload
                  class="avatar-uploader"
                  action=""
                  :show-file-list="false"
                  :http-request="uploadFile"
                >
                  <img v-if="url" :src="url"  class="avatar" style="width: 140px;height: 140px" >
                  <div v-if="!url" class="avatar-uploader-icon">
                    <i class="el-icon-upload"></i>
                  </div>
                  <div class="avatar-uploader-overlay">
                    <i class="el-icon-plus"></i>
                  </div>
                </el-upload>
              </div>
            </div>
            <el-divider></el-divider>
            <div class="user-info text item">
              <el-row :gutter="20">
                <el-col :span="8" :xs="24">
                  <span><i class="el-icon-user-solid"></i>账号名称</span>
                </el-col>
                <el-col :span="8" :xs="24">
                  <span>&nbsp;</span>
                </el-col>
                <el-col :span="8" :xs="24" class="col-right">
                  <span>{{tableData[0].userName}}</span>
                </el-col>
              </el-row>
            </div>
            <el-divider></el-divider>
            <div class="user-info text item">
              <el-row :gutter="20">
                <el-col :span="8" :xs="24">
                  <span><i class="el-icon-user-solid"></i>用户姓名</span>
                </el-col>
                <el-col :span="8" :xs="24">
                  <span>&nbsp;</span>
                </el-col>
                <el-col :span="8" :xs="24" class="col-right">
                  <span>{{tableData[0].userRname}}</span>
                </el-col>
              </el-row>
            </div>
            <el-divider></el-divider>
            <div class="user-info text item">
              <el-row :gutter="20">
                <el-col :span="8" :xs="24">
                  <span><i class="el-icon-user-solid"></i>用户性别</span>
                </el-col>
                <el-col :span="8" :xs="24">
                  <span>&nbsp;</span>
                </el-col>
                <el-col :span="8" :xs="24" class="col-right">
                  <span>{{tableData[0].userGender}}</span>
                </el-col>
              </el-row>
            </div>
            <el-divider></el-divider>
            <div class="user-info text item">
              <el-row :gutter="20">
                <el-col :span="8" :xs="24">
                  <span><i class="iconfont icon-jiaose"></i>所属角色</span>
                </el-col>
                <el-col :span="7" :xs="24">
                  <span>&nbsp;</span>
                </el-col>
                <el-col :span="9" :xs="24" class="col-right">
                  <span>{{tableData[0].roleName}}</span>
                </el-col>
              </el-row>
            </div>
            <el-divider></el-divider>
            <div class="user-info text item">
              <el-row :gutter="20">
                <el-col :span="8" :xs="24">
                  <span><i class="iconfont icon-shouji"></i>联系电话</span>
                </el-col>
                <el-col :span="8" :xs="24">
                  <span>&nbsp;</span>
                </el-col>
                <el-col :span="8" :xs="24" class="col-right">
                  <span>{{tableData[0].userTel}}</span>
                </el-col>
              </el-row>
            </div>
            <el-divider></el-divider>
            <div class="user-info text item">
              <el-row :gutter="20">
                <el-col :span="8" :xs="24">
                  <span><i class="iconfont icon-youxiang"></i>电子邮箱</span>
                </el-col>
                <el-col :span="7" :xs="24">
                  <span>&nbsp;</span>
                </el-col>
                <el-col :span="9" :xs="24" class="col-right">
                  <span>{{tableData[0].userEmail}}</span>
                </el-col>
              </el-row>
            </div>
            <el-divider></el-divider>
            <div class="user-info text item">
              <el-row :gutter="20">
                <el-col :span="8" :xs="24">
                  <span><i class="iconfont icon-riqi"></i>注册日期</span>
                </el-col>
                <el-col :span="7" :xs="24">
                  <span>&nbsp;</span>
                </el-col>
                <el-col :span="9" :xs="24" class="col-right">
                  <span>{{tableData[0].registrationDate}}</span>
                </el-col>
              </el-row>
            </div>
            <el-divider></el-divider>
          </el-card>
        </el-col>
        <el-col :span="16" :xs="24" :offset="2">
          <el-card class="box-card1">
              <template>
                <el-tabs v-model="activeName" @tab-click="reset('updateForm','ruleFormPwd')"
                         style="line-height: 10px;padding: 1px">
                  <el-tab-pane label="基本资料" name="first">
                    <el-form :label-position="labelPosition" :model="updateUserInfo" ref="updateForm"
                             :rules="updateUserRules" label-width="100px" class="demo-ruleForm" >
                      <el-form-item label="用户姓名" class="label-font" prop="userRname">
                        <el-input v-model="updateUserInfo.userRname" autocomplete="off" clearable></el-input>
                      </el-form-item>
                      <el-form-item label="联系电话" class="label-font">
                        <el-input v-model="updateUserInfo.userTel" autocomplete="off" clearable></el-input>
                      </el-form-item>
                      <el-form-item label="电子邮箱" class="label-font">
                        <el-input v-model="updateUserInfo.userEmail" autocomplete="off" clearable></el-input>
                      </el-form-item>
                      <el-form-item label="性别" class="label-font">
                        <el-radio-group v-model="updateUserInfo.userGender">
                          <el-radio label="男">男</el-radio>
                          <el-radio label="女">女</el-radio>
                        </el-radio-group>
                      </el-form-item>
                      <el-form-item>
                        <el-button type="primary" @click="updateUser('updateForm')">保存</el-button>
                        <el-button type="danger" @click="closeUserProfile('updateForm')">返回</el-button>
                      </el-form-item>
                    </el-form>
                  </el-tab-pane>
                  <el-tab-pane label="修改密码"  name="second">
                    <el-form :model="userPasswordInfo" :label-position="labelPosition" status-icon :rules="updatePwRules"  ref="ruleFormPwd" label-width="100px" class="demo-ruleForm">
                      <el-form-item label="旧密码" class="label-font" required prop="oldPassword">
                        <el-input type="password" v-model="userPasswordInfo.oldPassword" autocomplete="off"></el-input>
                      </el-form-item>
                      <el-form-item label="新密码" class="label-font" required prop="newPassword">
                        <el-input type="password" v-model="userPasswordInfo.newPassword" autocomplete="off"></el-input>
                      </el-form-item>
                      <el-form-item label="确认密码" class="label-font" required prop="confirmPassword">
                        <el-input type="password" v-model="userPasswordInfo.confirmPassword" autocomplete="off"></el-input>
                      </el-form-item>
                      <el-form-item>
                        <el-button type="primary" @click="updateUserPassword('ruleFormPwd')">保存</el-button>
                        <el-button type="danger" @click="closeUserProfile('ruleFormPwd')">返回</el-button>
                      </el-form-item>
                    </el-form>
                  </el-tab-pane>
                </el-tabs>
              </template>
          </el-card>
        </el-col>
      </el-row>
    </div>

</template>

<script>
    export default {
        name: "UserProfile",
        data(){
          const _self = this
          var checkOldPassword = (rule, value, callback) => {
             _self.$http.post("/api/checkPwd",{oldPassword:_self.userPasswordInfo.oldPassword}).then(resp=>{
               if(resp.data.code==200){
                 callback()
               }else {
                 callback(new Error('密码错误！'));
               }
             })
          };
          var checkNewPassword = (rule, value, callback) => {
              if(value==='' || typeof value==='undefined'){
                callback(new Error('请输入密码！'))
              }else{
                callback()
              }
          };
          var checkConfirmPassword = (rule, value, callback) => {
            if(value==='' || typeof value==='undefined'){
              callback(new Error('请输入密码！'))
            }else{
              if(_self.userPasswordInfo.newPassword == _self.userPasswordInfo.confirmPassword){
                callback()
              }else {
                callback(new Error('两次密码不一致！'))
              }
            }
          };

          return {
            tableData:[],
            satoken:undefined,
            userInfo:'个人信息',
            userProfileQuery:{
              userName: undefined,
              userRname: undefined,
              roleName: undefined,
              userStatus: undefined,
              currentPage: 1,
              pageSize: 5
            },
            updateUserInfo: {
              userId: undefined,
              userName: undefined,
              userRname: undefined,
              userGender: undefined,
              roleName: undefined,
              userTel: undefined,
              userEmail: undefined,
              userStatus: undefined
            },
            url:undefined,
            activeName:'first',
            labelPosition:'right',
            userPasswordInfo:{
              oldPassword:undefined,
              newPassword:undefined,
              confirmPassword:undefined
            },
            updateUserRules: {
              userRname: [
                {required: true, message: '请输入姓名', trigger: 'blur'},
              ]
            },
            updatePwRules: {
              oldPassword: [
                { validator: checkOldPassword, trigger: 'blur' }
              ],
              newPassword: [
                { validator: checkNewPassword, trigger: 'blur' }
              ],
              confirmPassword: [
                { validator: checkConfirmPassword, trigger: 'blur' }
              ]
            }
          };
        },
      methods:{
          checkUserInfo() {
            const _self = this
            _self.userProfileQuery.userName = sessionStorage.getItem('username')
            _self.$http.get("/api/user",{
              params: _self.userProfileQuery
            }).then(function (resp) {
              console.log(resp)
              if (resp.data.code == 200) {
                _self.tableData = resp.data.data.userInfos
              }
              _self.updateUserInfo.userId = _self.tableData[0].userId
              _self.updateUserInfo.userName = _self.tableData[0].userName
              _self.updateUserInfo.userRname = _self.tableData[0].userRname
              _self.updateUserInfo.userGender = _self.tableData[0].userGender
              _self.updateUserInfo.userTel = _self.tableData[0].userTel
              _self.updateUserInfo.userEmail = _self.tableData[0].userEmail
              if(_self.tableData[0].userStatus === 1) _self.updateUserInfo.userStatus = '正常'
              else _self.updateUserInfo.userStatus = '冻结'
              _self.updateUserInfo.roleName = _self.tableData[0].roleName
            })
            _self.url = sessionStorage.getItem('imgUrl')
            if(_self.url.substring(0,7)==="http://") {
              _self.url = _self.url
            } else {
              _self.url = "http://localhost:8088"+ _self.url
            }
          },
        uploadFile(param){
          const _self = this
          let file = param.file
          //创建表单数据对象
          let formData = new FormData();
          formData.append("file",file)
          _self.$http.post("/api/upload",formData).then(resp=>{
            if(resp.data.code==200){
              console.log(resp)
              _self.$message.success(resp.data.msg)
              _self.url = "http://localhost:8088"+resp.data.data
              _self.$emit("change_url",_self.url)
              sessionStorage.removeItem('imgUrl')
              sessionStorage.setItem('imgUrl',_self.url)
            }else {
              _self.$message.error(resp.data.msg)
            }
          })
        },
        updateUser(formName) {
            const _self = this
          _self.$refs[formName].validate((valid) => {
            if (valid) {
              _self.$http.put("/api/user",_self.updateUserInfo).then(resp=>{
                if(resp.data.code==200){
                  _self.$message.success(resp.data.msg)
                  _self.checkUserInfo()
                }
              })
            } else {
              console.log('error submit!!');
              return false;
            }
          });
        },
        updateUserPassword(updatePwdForm){
          const _self = this
          _self.$refs[updatePwdForm].validate((valid) => {
            if (valid) {
             //表单验证通过就发送请求
              _self.$http.put("/api/pwd",_self.userPasswordInfo).then(resp=>{
                if(resp.data.code==200){
                  //将表单清空
                  _self.$refs[updatePwdForm].resetFields();
                  //退出
                  _self.$http.get("/api/logout").then(function (resp) {
                    sessionStorage.clear()
                    _self.$router.replace("/login")
                    _self.$router.go(0)
                  })
                }else {
                  _self.$message.error(resp.data.msg)
                }
              })
            } else {
              return false;
            }
          });
        },
        closeUserProfile(formName) {
            const _self = this
          _self.$refs[formName].resetFields();
          _self.$router.push('/index/home');
        },
        reset(formName1,formName2){
            const _self = this
          _self.$refs[formName1].resetFields();
          _self.$refs[formName2].resetFields();
          _self.checkUserInfo()
        }
      },
      mounted() {
        this.checkUserInfo();
      }
    }
</script>

<style scoped>
  .user-info{
    text-align: left;
    line-height: 10px;
  }
  .user-img{
    text-align: center;
    line-height: 10px;
  }
  .col-right{
    text-align: right;
  }
  .text {
    font-size: 14px;
  }

  .item {
    padding: 18px 0;
  }

  .box-card {
    width: 400px;
  }
  .el-divider--horizontal{
    margin: 0px;
  }
  .box-card1{
    width: 800px;
  }
  .el-tabs__nav{
    padding: 1px;
  }
  .el-form-item{
    text-align: left;
  }
  .label-font{
    font-weight: bold;
  }
  .avatar-uploader {
    width: 140px;
    height: 140px;
    border: 1px solid #d9d9d9;
    border-radius: 50%;
    overflow: hidden;
    position: relative;
    margin-left: 100px;
  }

  .avatar {
    width: 100%;
    height: 100%;
    display: block;
  }

  .avatar-uploader-icon {
    width: 100%;
    height: 100%;
    line-height: 140px;
    text-align: center;
    font-size: 36px;
    color: #8c939d;
  }

  .avatar-uploader-overlay {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.24);
    color: rgba(255, 255, 255, 0.9);
    font-size: 28px;
    text-align: center;
    line-height: 140px;
    border-radius: 50%;
    opacity: 0;
    transition: opacity 0.3s ease;
    cursor: pointer;
  }

  .avatar-uploader:hover .avatar-uploader-overlay {
    opacity: 1;
  }
</style>
