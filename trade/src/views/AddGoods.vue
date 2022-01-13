<template>
  <div class="add-goods">
    <el-page-header @back="goBack" content="Upload Goods" title="Back"> </el-page-header>
    <div class="add-pic">
        <el-upload action="" :on-change="handleChange"  :auto-upload="false" :on-remove="handleRemove"  accept="image/png,image/jpg,image/jpeg"
                  list-type="picture-card" :limit="1" :class="{hide:hideUpload}">
          <i class="el-icon-plus"></i>
        </el-upload>
      <!-- <form>
        <input type="file" name="file" @change="getFile($event)" />
        <el-button  type="primary" style="width: 120px" @click="submit($event)">
          Submit Pic
        </el-button>
      </form>
      <img v-if="good.pic!=''" :src="good.pic" style="width:50px;height:50px;"/> -->
    </div>
    <div class="index">
      <div class="form">
        <el-form :inline="true" label-width="80px">
          <el-form-item label="Category">
            <el-radio-group v-model="catalog">
              <el-radio :label="item.name" v-for="item in allCatalog" :key="item.id"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="Name">
            <el-input v-model="good.gname"></el-input>
          </el-form-item>
          <el-form-item label="Description">
            <el-input v-model="good.remark"></el-input>
          </el-form-item>
          <el-form-item label="Price">
            <el-input v-model="good.price" oninput ="value=value.replace(/[^0-9.]/g,'')"></el-input>
          </el-form-item>
        </el-form>
        <div class="option">
          <el-button type="primary" style="width: 120px" @click="confirmSubmit">Submit</el-button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      hideUpload:false,
      catalog: "",
      allCatalog:[],
      file: "",
      username: "xmx",
      pic: "",
      good: {
        id: null,
        uid: null,
        gname: "",
        price: null,
        pic: "",
        status: 0,
        remark: "",
        cid: null,
      },
    };
  },
  methods: {
    goBack() {
      this.$router.push("/allGoods");
    },
    // getFile: function (event) {
    //   this.file = event.target.files[0];
    //   console.log("getFile", this.file);
    // },
    handleRemove(file, fileList){
      setTimeout(() => {
        this.hideUpload=false
      }, 990)
    },
    handleChange(file, fileList) {
      this.hideUpload=true
      // let that = this;
      console.log(file, fileList)
      let formdata = new FormData()
      fileList.map(item => { //fileList本来就是数组，就不用转为真数组了
        formdata.append("file", item.raw)  //将每一个文件图片都加进formdata
      })
      let that = this;
      this.$axios
        .post("/upload/image", formdata)
        .then(function (response) {
          that.good.pic = response.data.data;
          console.log("获取到了图片链接", that.good.pic);
          that.$message({
            message: "upload success",
            type: "success",
          });
        })
    },
    // submit: function (event) {
    //   let that = this;
    //   //阻止元素发生默认的行为
    //   event.preventDefault();
    //   let formData = new FormData();
    //   formData.append("file", this.file);
    //   this.$axios
    //     .post("/api/upload/image", formData)
    //     .then(function (response) {
    //       that.good.pic = response.data.data;
    //       console.log("获取到了图片链接", that.good.pic);
    //       that.$message({
    //         message: "upload success",
    //         type: "success",
    //       });
    //     })
    // },
    showUserInfo() {
      console.log("showUserInfo():", this.user);
    },
    confirmSubmit() {
      console.log("confirmSubmit",this.good,this.catalog);
      let select = this.catalog;
      this.allCatalog.forEach(e=>{
        if(e.name==select){
          this.good.cid=e.id
        }
      })
      console.log("商品准备发布：this.good:", this.good);
      this.good.gname=this.good.gname.replace(/^\s\s*/, '').replace(/\s\s*$/, '')
      this.good.remark=this.good.remark.replace(/^\s\s*/, '').replace(/\s\s*$/, '')
      if(this.good.gname=='' || this.good.price==null || this.good.pic=='' || this.good.remark==''){
        this.$message.error("Please input full info about the good!");
      }else{
        this.$axios.post("/Goods/addGood", this.good).then((res) => {
          console.log("发布成功", res);
          if(res.data.code==200){
            this.$message({
              message: "upload success",
              type: "success",
            });
          }
        });
        this.$router.go(0);
        // this.$bus.$emit('hideSearch',true)
        // window.location.reload()
      }
    },
  },
  created() {
    console.log("created")
    // this.$bus.$emit('hideSearch',true)
    this.good.uid = localStorage.getItem("userId");
    this.allCatalog=this.$store.getters.getCatalog
    // console.log("catalog",JSON.parse(localStorage.getItem('catalog')))
  },
  // mounted() {
  //   console.log("mounted")
  //   console.log(this.$store.getters.getCatalog)
  //   this.allCatalog=this.$store.getters.getCatalog
  // },
};
</script>

<style>
.hide .el-upload--picture-card {
    display: none;
}
.add-goods {
  margin: 0 auto;
  padding: 12px 12px;
  width: 1200px;
  height: 800px;
  background-color: #fff;
}
.index {
  display: flex;
}
.index .imgBox {
  width: 200px;
}
.index .imgBox img {
  width: 100%;
}
.index .form {
  margin-top: 30px;
  margin-left: 30px;
  width: 800px;
  height: 600px;
}
.index .option {
  display: flex;
  justify-content: center;
}
.add-pic {
  width: 400px;
  margin: 10px auto;
}
.index {
  width: 800px;
  margin: 10px auto;
}
</style>