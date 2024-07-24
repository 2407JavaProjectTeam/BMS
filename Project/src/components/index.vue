<template>
  <el-container>
    <el-aside width="13vw">
      <el-row class="tac">
        <el-col :span="24">
          <el-menu
            default-active="/index/home"
            class="el-menu-vertical-demo"
            background-color="#2A3F54"
            text-color="#fff"
            active-text-color="#ffd04b"
            router>
            <!-- 一级菜单-->
            <!-- index 菜单索引-->
            <template v-for="x in nodes">
              <template v-if="x.child!=null">
                <el-submenu :index="x.url" class="myMenuItem">
                  <!--一级菜单-->
                  <template slot="title">
                    <i :class="x.icon"></i>
                    <span>{{x.name}}</span>
                  </template>
                  <!--一级菜单下面的子菜单-->
                  <template v-for="y in x.child">
                    <!-- index 路由地址-->
                    <el-menu-item :index="y.url">
                      <template>
                        <i :class="y.icon"></i>
                        {{y.name}}
                      </template>
                    </el-menu-item>
                  </template>
                </el-submenu>
              </template>
              <!--不含子菜单的一级菜单-->
              <template v-else>
                <el-menu-item :index="x.url" class="myMenuItem">
                  <i :class="x.icon"></i>
                  <span slot="title">{{x.name}}</span>
                </el-menu-item>
              </template>
            </template>
          </el-menu>
        </el-col>
      </el-row>
    </el-aside>
    <el-container>
      <el-header height="8vh">
        <div class="block" id="userImage">
          <el-image :src="url" style="width: 50px; height: 50px;border-radius: 50%;" fit="fill">
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
          <el-menu active-text-color="EDEDED"  class="el-menu-demo" mode="horizontal" background-color="#EDEDED" router>
            <el-submenu index="" style="text-align: left">
              <template slot="title">{{username}}</template>
              <el-menu-item index="/index/userProfile">
                <el-link :underline="false">个人中心</el-link>
              </el-menu-item>
              <el-menu-item @click="logout">
                <el-link :underline="false">退出</el-link>
              </el-menu-item>
            </el-submenu>
          </el-menu>
        </div>

      </el-header>
      <el-main height="92vh">
        <router-view @change_url="changUrl($event)"></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  export default {
    name: "Index",
    data(){
      return{
        url:undefined,
        username:"",
        nodes:[]
      }
    },
    methods: {
      //查询菜单信息
      queryPermissions(){
          const _self = this
          //http://localhost:8088/api/permisssion/admin
          _self.$http.get("/api/permisssion/"+_self.username)
            .then(function (resp) {
              if(resp.data.code==200){
                _self.nodes = resp.data.data
              }else if(resp.data.code==11011){
                _self.$confirm('token异常，请重新登录！', '提示',{
                  confirmButtonText: '确定',
                  type: 'warning',
                  showCancelButton: false,
                  // showClose: false,
                  // closeOnPressEscape: false,
                  // closeOnClickModal: false
                }).then(function () {
                    _self.$router.replace("/login")
                }).catch(function () {
                    _self.$router.replace("/login")
                })
              }

          })
        },
      //退出
      logout(){
        const _self = this
        _self.$http.get("/api/logout").then(function (resp) {
            sessionStorage.clear()
            _self.$router.replace("/login")
            _self.$router.go(0)
        })
      },
      changUrl(url){
        this.url = url
      }
    },
    //钩子函数（简单理解为页面渲染完毕就执行）
    mounted() {
      this.username = sessionStorage.getItem("username")
      this.queryPermissions()
      this.url = sessionStorage.getItem("imgUrl")
      if(this.url.substring(0,7)==="http://") {
        this.url = this.url
      } else {
        this.url = "http://localhost:8088"+ this.url
      }
    }
  }
</script>

<style scoped>
  .el-aside {
    background-color: #2A3F54;
    color: #333;
    text-align: center;
    line-height: 200px;
    /*height: 752px;*/
    height: 100vh;
  }
  .el-header {
    background-color: #EDEDED;
    color: #333;
    text-align: center;
    /*line-height: 65px;*/
    /*line-height: 15vh;*/

  }
  .el-main {
    background-color: #F7F7F7;
    color: #333;
    text-align: center;
    line-height: 160px;
  }
  body > .el-container {
    /*margin-bottom: 40px;*/
    width: 100vw;
    height: 100vh;
  }
  .userName{
    /*position: absolute;*/
    float: right;
    margin-right: -105px;
    top:2px;
    width: 64px;
    height: 65px;
    text-align: left;
  }
  #userImage{
    position: absolute;
    /*float: right;*/
    right: 10px;
    top:-5px;
  }
  .el-menu-demo{
    display: inline-block;
    background-color: #EDEDED;
    text-align: left;
  }
  .myMenuItem {
    /*height: 56px;*/
    /*line-height: 56px;*/
    /*list-style: none;*/
    text-align: left;
  }

</style>
