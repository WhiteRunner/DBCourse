module.exports = {
  publicPath: process.env.NODE_ENV === 'production' ? '/trade/' : '/', // 输出的index.html引入的资源路径前面增加/trade
  outputDir: 'trade', // 打包输出的文件夹名
  indexPath: 'index.html',
  // devServer: {
  //   // port: 8081,   // 端口号
  //   proxy: {  //配置跨域
  //     '/api': {
  //       // target: 'http://127.0.0.1:8099/',  //这里后台的地址模拟的;应该填写你们真实的后台接口
  //       target: 'http://110.40.199.128:8099/',
  //       changOrigin: true,  //模拟同源请求
  //       pathRewrite: {
  //         /* 重写路径，当我们在浏览器中看到请求的地址为：http://localhost:8080/api/core/getData/userInfo 时
  //           实际上访问的地址是：http://121.121.67.254:8185/core/getData/userInfo,因为重写了 /api
  //          */
  //         '^/api': ''
  //       }
  //     },
  //   }
  // }
};