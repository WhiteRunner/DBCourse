<template>
  <div class="all-goods">
    <el-main>
      <div class="catalog">
        <el-tabs tab-position="left" @tab-click="selectCatalog">
          <!-- 所有商品模块 -->
          <el-tab-pane label="All Goods">
            <div class="no-goods" v-if="good.length==0">
              <img src="../assets/no-goods.png" alt="no-goods"/>
              <div>No Goods</div>
            </div>
            <el-row>
              <el-col :span="8" v-for="(item, index) in good" :key="index">
                <el-card
                  :body-style="{ padding: '0px' }"
                  class="good-list-item"
                >
                  <div class="img-box" @click="showDetail(index, item)">
                    <!-- <img :src="testUrl" class="image" /> -->
                    <img :src="item.pic" class="image" />
                  </div>
                  <div style="padding: 14px" @click="showDetail(index, item)">
                    <span>{{ item.gname | ellipsis}}</span>
                    <span style="float:right" class="desc">{{item.username}}</span>
                    <img src="../assets/avatar.png" alt="" style="width: 20px;height: 20px;float:right;margin-right:10px"/>
                    <div class="bottom clearfix">
                      <time class="desc">{{ item.remark | ellipsis }}</time>
                      <el-button type="text" class="button">
                        <span style="color: var(--primary-color); font-size: 18px">￥{{ item.price }}</span>
                      </el-button>
                    </div>
                  </div>
                </el-card>
              </el-col>
            </el-row>
          </el-tab-pane>
          <!-- 商品分类模块 -->
          <el-tab-pane v-for="item in catalog" :label="item" :key="item.id">
            <div class="no-goods" v-if="good.length==0">
              <img src="../assets/no-goods.png" alt="no-goods"/>
              <div>No Goods</div>
            </div>
            <el-row>
              <el-col :span="8" v-for="(item, index) in good" :key="index">
                <el-card :body-style="{ padding: '0px' }" class="good-list-item">
                  <div class="img-box" @click="showDetail(index, item)">
                    <img :src="item.pic" class="image" />
                  </div>
                  <div style="padding: 14px" @click="showDetail(index, item)">
                    <span>{{ item.gname | ellipsis }}</span>
                    <span style="float:right" class="desc">{{item.username}}</span>
                    <img src="../assets/avatar.png" alt="" style="width: 20px;height: 20px;float:right;margin-right:10px"/>
                    <div class="bottom clearfix">
                      <time class="desc">{{ item.remark | ellipsis }}</time>
                      <el-button type="text" class="button">
                        <span style="color: var(--primary-color); font-size: 18px">￥{{ item.price }}</span>
                      </el-button>
                    </div>
                  </div>
                </el-card>
              </el-col>
            </el-row>
          </el-tab-pane>
        </el-tabs>
        <!-- 分页模块 -->
        <!-- 不分类时 -->
        <div class="pagination" v-if="isSort == false">
          <el-pagination
            background
            layout="prev, pager, next"
            :page-size="9"
            :total="total"
            :current-page.sync="currentPage"
            @current-change="page"
          >
          </el-pagination>
        </div>
        <!-- 分类时 -->
        <div class="pagination" v-else>
          <el-pagination
            background
            layout="prev, pager, next"
            :page-size="9"
            :total="totalSort"
            :current-page.sync="currentPage"
            @current-change="pageSort"
          >
          </el-pagination>
        </div>
      </div>
    </el-main>
  </div>
</template>
<script>
export default {
  data() {
    return {
      testUrl: "http://localhost:8281/img/2.jpg",
      currentPage:1,
      pageSize: 9, //每页9条记录
      total: 0, //总共多少条记录
      totalSort: 30, //分类时总共多少条记录
      isSort: false, //是否分类，默认不分类，查找所有
      selectSort: 0,
      catalog: [],
      catalogDetail: [],
      search:'',
      good: [
        // {
        //   id: 1,
        //   uid: 1,
        //   gname: "富士xe4",
        //   price: 2888,
        //   pic: "",
        //   status: 1,
        //   remark: "99新，箱说全，不议价",
        //   cid: 1,
        //   username:xyy 
        // },
      ],
    };
  },
  filters: {
    ellipsis(value) {
      //字数过滤器，用于限制商品详情的字数显示
      if (!value) return "";
      if (value.length > 22) {
        return value.slice(0, 22) + "...";
      }
      return value;
    },
  },
  methods: {
    //点击商品时触发（跳转到商品详情页）
    showDetail(index, item) {
      console.log("获取到了商品详情", index, item);
      this.$router.push({
        path: "/goodsDetail",
        query: {
          id: item.id,
        },
      });
    },
    //选择分类时触发（发起axios请求重新渲染页面）
    // selectCatalog(tab) {
    //   this.currentPage=1
    //   console.log("切换",this.search,this.currentPage)
    //   if (tab.index != 0) {
    //     this.isSort = true;
    //     var label=tab.label
    //     this.catalogDetail.forEach(e=>{
    //       if(e.name==label){
    //         this.selectSort=e.id
    //       }
    //     })
    //     // this.selectSort = tab.index;
    //     // console.log(tab.label);
    //     // console.log("选择的cid", this.selectSort);
    //     //查询所有商品
    //     this.$axios
    //       .get("/api/Goods/getList",{
    //       params:{        
    //           status:0,     
    //           pageNo:1,
    //           pageSize:9,
    //           cid:this.selectSort,
    //           gname:this.search
    //         }
    //       })
    //       .then((res) => {
    //         this.good = res.data.data;
    //         this.totalSort = res.data.dataCount
    //       });
    //     //查询商品数量（分类时）
    //     // this.$axios.get("/api/Goods/goodsNum/" + this.selectSort).then((res) => {
    //     //   this.totalSort = res.data.data;
    //     // });
    //     // this.$axios.get("/api/Goods/goodsNum/" + this.selectSort+"/"+this.search).then((res) => {
    //     //   this.totalSort = res.data.data;
    //     // });
    //   } else {
    //     this.selectSort=0
    //     this.isSort = false;
    //     console.log("不分类");
    //     this.$axios.get("/api/Goods/goodsNum/" + this.selectSort+"/"+this.search).then((res) => {
    //       this.total = res.data.data;
    //     });
    //     this.page(1);
    //   }
      
    // },
    selectCatalog(tab) {
      this.currentPage=1
      console.log("切换",this.search,this.currentPage)
      this.selectSort=0
      if (tab.index != 0) {
        this.isSort = true;
        var label=tab.label
        this.catalogDetail.forEach(e=>{
          if(e.name==label){
            this.selectSort=e.id
          }
        })}
      this.$axios
        .get("/Goods/getList",{
        params:{        
            status:0,     
            pageNo:1,
            pageSize:9,
            cid:this.selectSort==0 ? '' : this.selectSort,
            gname:this.search
          }
        })
        .then((res) => {
          this.good = res.data.data;
          if(this.selectSort==0){
            this.isSort = false
            this.total = res.data.dataCount;
          }else{
            this.totalSort = res.data.dataCount;
          }
        });
      
    },
    //不分类时的查找
    page(currentPage) {
      console.log("当前页数：", currentPage);
      this.$axios
        .get("/Goods/getList",{
          params:{          
            status:0,   
            pageNo:currentPage,
            pageSize:9,
            gname:this.search
          }
        })
        .then((res) => {
          this.good = res.data.data;
          window.scroll(0, 0);
        });
    },
    //分类时的查找
    pageSort(currentPage) {
      console.log("pageSort", currentPage);
      this.$axios.get("/Goods/getList",{
          params:{      
            status:0,       
            pageNo:currentPage,
            pageSize:9,
            cid: this.selectSort,
            gname:this.search
          }
        })
        .then((res) => {
          console.log("分类时的分页数据", res.data.data);
          this.good = res.data.data;
          window.scroll(0, 0);
        });
    },
  },
  mounted() {
    this.$bus.$emit('hideSearch',false)
    //获取类别
    this.$axios.get("/Category/getList").then((res) => {
      var arr=res.data.data
      arr.forEach(element => {
        this.catalog.push(element.name)
      });
      this.catalogDetail=arr
      this.$store.commit('setCatalog',arr)
      // console.log("vuex",this.$store.state.catalog)
    });
    console.log("mounted")
    //获取商品信息
    let input = this.$route.query.input;
    this.search = input==undefined ? '' : input
    this.$axios.get("/Goods/getList",{
      params:{      
        status:0,       
        pageNo:1,
        pageSize:9,
        gname:this.search
      }
    }).then((res) => {
        // console.log("/api/Goods/getList", res);
        this.good = res.data.data;
        this.total = res.data.dataCount;
        // console.log("good", this.good);
      })
      .catch((err) => {
        console.log(err);
      });
    //获取商品总条数（用于分页）
    // this.$axios.get("/api/Goods/goodsNum").then((res) => {
    //   this.total = res.data.data;
    //   console.log("个数为",this.total);
    // });
  },
  watch: {
    $route() {
      let input = this.$route.query.input;
      // console.log("搜索",input)
      this.search = input==undefined ? '' : input
      this.$bus.$emit('changeInput',this.search)
      this.$axios
          .get("/Goods/getList",{
          params:{           
              status:0, 
              pageNo:1,
              pageSize:9,
              cid:this.selectSort==0 ? '' : this.selectSort,
              gname:this.search
            }
          })
          .then((res) => {
            this.good = res.data.data;
            if(this.selectSort==0){
              this.total = res.data.dataCount;
            }else{
              this.totalSort = res.data.dataCount;
            }
          });



      // this.$axios.get("/api/Goods/goodsNum/" + this.selectSort+"/"+this.search).then((res) => {
      //   if(this.selectSort==0){
      //      this.total = res.data.data;
      //   }else{
      //      this.totalSort = res.data.data;
      //   }
      // });

    },
    
  },
};
</script>
<style scoped>
/* 图片 */
.img-box {
  height: 240px;
}
.img-box img {
  width: 100%;
}
.catalog >>> .el-tabs__item {
  width: 180px;
}
.catalog >>> .el-tabs__item:hover {
  color: var(--primary-color) !important;
  font-size: 16px;
  font-weight: 600;
}
.catalog >>> .is-active {
  color: var(--primary-color) !important;
  font-size: 16px;
  font-weight: 600;
}
.el-main {
  margin: 0 auto;
  padding: 0;
  width: 1200px;
}
/* 分类菜单 */
.catalog {
  background-color: #fff;
  padding: 10px 0;
  height: 1200px;
}
.catalog-item {
  width: 100%;
  text-align: center;
  padding: 10px 0;
  font-weight: 600;
}
.catalog-item:hover {
  border: solid 1px #0fb9b1;
  color: #079992;
  font-size: 18px;
}
.catalog-item span {
  cursor: pointer;
}
/* 商品列表 */
.good-list-item {
  width: 280px;
}
.desc {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}
.good-list-item {
  user-select: none;
  cursor: pointer;
}
/* hover实现放大图片效果 */
.good-list-item .image {
  /* width: 100%; */
  height: 100%;
  object-fit: cover;

  /* display: block; */
  cursor: pointer;
  transition: all 0.8s 0.1s;
}
.good-list-item:hover .image {
  transform: scale(1.1);
}
.good-list-item .img-box {
  overflow: hidden;
}
/* ------ */

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}
.el-col {
  display: flex;
  justify-content: center;
  margin: 20px 0;
}
/* 分页 */
.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 2% 0;
}
.pagination
  >>> .el-pagination.is-background
  .el-pager
  li:not(.disabled).active {
  background-color: var(--primary-color);
  color: #ffffff;
}
.pagination >>> .el-pagination.is-background .el-pager li:not(.disabled) {
  /* background-color: var(--primary-color); */
  color: #424242;
}
.no-goods {
  width: 100%;
  height: 500px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.no-goods img {
  width: 300px;
}

/* .all-goods{
  height: 1200px;
} */
</style>