<template>
  <div>
    <div style="line-height: 50px">
      <el-autocomplete style="width: 600px"
                       placeholder="请输入内容" v-model="input" class="input-with-select" clearable>
        <el-select style="width: 110px" v-model="value" slot="prepend" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        <el-button slot="append" icon="el-icon-search" ></el-button>
      </el-autocomplete>
    </div>
    <el-table
      :data="tableData"
      border
      style="width: 100%;line-height: 40px"
      @selection-change="selectChange"
      :default-sort = "{prop: 'date', order: 'descending'}"
    >
      <el-table-column
        prop="title"
        label="书名"
        sortable
        width="150">
      </el-table-column>
      <el-table-column
        prop="author"
        label="作者"
        sortable
        width="120">
      </el-table-column>
      <el-table-column
        prop="book_img"
        label="图书封面"
        width="150">
      </el-table-column>
      <el-table-column
        prop="publicationDate"
        label="出版日期"
        width="150">
      </el-table-column>
      <el-table-column
        prop="isbn"
        label="ISBN号"
        sortable
        width="120">
      </el-table-column>
      <el-table-column
        prop="categoryList[0].categoryName"
        label="图书分类"
        sortable
        width="100">
      </el-table-column>
      <el-table-column
        prop="bookNum"
        label="图书余量"
        sortable
        width="100">
      </el-table-column>
      <el-table-column
        prop="bookBorrowNum"
        label="图书借阅量"
        sortable
        width="100">
      </el-table-column>

      <el-table-column
        label="操作"
        width="160">
        <template slot-scope="scope">
          <el-button @click="openReturnBookDialog" type="info" size="medium" v-show="scope.row.userName!='admin'">借阅管理</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
        :current-page="bookQueryParams.currentPage"
        :page-sizes="[5, 7, 10]"
        :page-size="bookQueryParams.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totals" class="el-pagination"
        @current-change="currentChange"
        @size-change="sizeChange"
      >
      </el-pagination>
    </div>

    <!--新增模态框-->
    <el-dialog title="图书归还" :visible.sync="isOpenReturnDialog" style="line-height: 0px" class="el-dialog__header">
      <el-table
        :data="tableData"
        border
        style="width: 100%;line-height: 0px"
        @selection-change="selectChange"
        :default-sort = "{prop: 'date', order: 'descending'}"
      >
        <el-table-column
          prop="userName"
          label="借阅人"
          sortable
          width="100">
        </el-table-column>
        <el-table-column
          prop="title"
          label="书名"
          sortable
          width="150">
        </el-table-column>
        <el-table-column
          prop="author"
          label="作者"
          sortable
          width="100">
        </el-table-column>
        <el-table-column
          prop="isbn"
          label="ISBN号"
          sortable
          width="120">
        </el-table-column>
        <el-table-column
          prop="borrowDate"
          label="借阅日期"
          sortable
          width="100">
        </el-table-column>

        <el-table-column
          label="操作"
          width="100">
          <template slot-scope="scope">
            <el-button @click="returnBook" type="primary" size="small" v-show="scope.row.userName!='admin'">图书归还</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="block">
        <el-pagination
          :current-page="bookRBQueryParams.currentPage"
          :page-sizes="[3, 5, 7]"
          :page-size="bookRBQueryParams.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="totals" class="el-pagination"
          @current-change="currentRBChange"
          @size-change="sizeRBChange"
        >
        </el-pagination>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "ReM",
    data() {
      return {
        value: '',
        input: '',
        options: [{
          value: 'title',
          label: '书名'
        }, {
          value: 'author',
          label: '作者'
        }, {
          value: 'isbn',
          label: 'ISBN号'
        }, {
          value: 'categoryName',
          label: '图书分类'
        }],
        //表格展示数据
        tableData: [],
        //分页参数
        totals: 0,//总条数
        formLabelWidth: '120px',
        isOpenReturnDialog: false,
        bookQueryParams: {
          title: undefined,
          author: undefined,
          isbn: undefined,
          currentPage: 1,
          pageSize: 5
        },
        bookRBQueryParams: {
          title: undefined,
          author: undefined,
          isbn: undefined,
          currentPage: 1,
          pageSize: 3
        },
      }
    },
    methods: {
      queryBookInfo() {
        const _self = this
        _self.$http.get("/api/book", {
          params: _self.bookQueryParams
        }).then(function (resp) {
          console.log(resp)
          if (resp.data.code == 200) {
            _self.tableData = resp.data.data.bookInfos
            _self.totals = resp.data.data.totals
          }
        })
      },
      currentChange(cpage) {
        const _self = this
        _self.bookQueryParams.currentPage = cpage
        _self.queryUserInfo()
      },
      sizeChange(psize) {
        const _self = this
        _self.bookQueryParams.pageSize = psize
        _self.bookQueryParams.currentPage = 1
        _self.queryBookInfo()
      },
      currentRBChange(cpage) {
        const _self = this
        _self.bookRBQueryParams.currentPage = cpage
        _self.queryUserInfo()
      },
      sizeRBChange(psize) {
        const _self = this
        _self.bookRBQueryParams.pageSize = psize
        _self.bookRBQueryParams.currentPage = 1
        _self.queryBookInfo()
      },
      openReturnBookDialog(){
        const _self = this
      _self.isOpenReturnDialog = true
      },
    },
    //页面渲染完毕执行
    mounted() {
      this.queryBookInfo()
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
