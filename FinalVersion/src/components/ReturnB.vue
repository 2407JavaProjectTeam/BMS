<template>
  <el-container style="height: 600px; border: 1px solid #eee">
    <el-container>
      <el-header  style=" height: 90px; line-height: 30px; text-align: left;">
        <el-form :inline="true"  class="demo-form-inline">
          <br/>
          <el-form-item label="书名">
            <el-input v-model="recordQueryParams.title"  placeholder="书名"></el-input>
          </el-form-item>
          <el-form-item label="作者">
            <el-input v-model="recordQueryParams.author"  placeholder="作者"></el-input>
          </el-form-item>
          <el-form-item label="分类">
            <el-input  v-model="recordQueryParams.cName" placeholder="分类号"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="conditionQuery" >查询</el-button>
          </el-form-item>
        </el-form>
      </el-header>

      <el-main>
        <el-table  :data="tableData" border
                   style="width: 100%;line-height: 40px"
                   :default-sort = "{prop: 'borrowDate', order: 'descending'}"
                   >

          <el-table-column prop="cname" label="类别" width="140">
          </el-table-column>
          <el-table-column prop="title" label="书名" width="120">
          </el-table-column>
          <el-table-column prop="author" label="作者">
          </el-table-column>
          <el-table-column prop="borrowDate" label="借阅时间" sortable>
          </el-table-column>
          <el-table-column prop="returnDate" label="归还时间">
          </el-table-column>
          <el-table-column prop="address" label="操作">
            <template slot-scope="scope">
            <el-button type="primary" @click="returnBook(scope.row)" >归还</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="block">
          <el-pagination
            :current-page="currentPage"
            :page-sizes="[2, 4, 5]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="totals" class="el-pagination"
            @current-change="currentChange"
            @size-change="sizeChange"
          >
          </el-pagination>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>
<style scoped>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
</style>

<script>
  export default {
    data() {
      return {
        value:"",
        //表格展示数据
        tableData: [],
        //分页参数
        currentPage:1,
        pageSize:2,
        totals:0,//总条数
        formLabelWidth: '120px',
        recordQueryParams:{
          title: undefined,
          author: undefined,
          cName: undefined,
          name: undefined,
          rb: 1,
          currentPage:1,
          pageSize:2,
        },
        returnInfo:{
          r_id: undefined,
          bookId: undefined,
          bookNum: undefined,
          bookBorrowNum: undefined,
          userId: undefined,
        }

      }
    },
    methods:{
      queryRecordInfo(){
        const _self = this
        _self.recordQueryParams.name =  sessionStorage.getItem("username")
        _self.$http.get("/api/record",{
          params: _self.recordQueryParams
        }).then(function (resp) {
            console.log(resp)
            if(resp.data.code==200){
              _self.tableData = resp.data.data.recordInfo
              _self.totals = resp.data.data.totals
            }
          }
        )
      },
      currentChange(cpage){
        this.recordQueryParams.currentPage = cpage
        this.currentPage = this.recordQueryParams.currentPage
        this.queryRecordInfo()
      },
      sizeChange(psize){
        this.recordQueryParams.pageSize = psize
        this.pageSize = this.recordQueryParams.pageSize
        this.recordQueryParams.currentPage = 1
        this.queryRecordInfo()
      },
      conditionQuery(){
        const _self = this
        _self.recordQueryParams.currentPage = 1
        _self.queryRecordInfo()
      },
      returnBook(row){
        const _self = this
        _self.$confirm('确认归还本书吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          _self.returnInfo.r_id = row.r_id
          _self.returnInfo.bookId = row.bookId
          _self.returnInfo.bookNum = row.bookNum
          _self.returnInfo.bookBorrowNum = row.bookBorrowNum
          _self.returnInfo.userId = sessionStorage.getItem("userid")

          // console.log(bookNum)
          // console.log(_self.returnInfo.bookNum)
          // console.log(_self.returnInfo.bookBorrowNum)
          // console.log(bookBN)
          _self.$http.put("/api/record",_self.returnInfo).then(resp=>{
            if(resp.data.code==200){
              _self.$message.success(resp.data.msg)
              _self.recordQueryParams.currentPage = 1
              _self.queryRecordInfo()
            }
          })
        })
      },
    },

    mounted() {
      this.queryRecordInfo()
    }
  };

</script>
