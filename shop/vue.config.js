module.exports = {
	//publicPath: './',
	productionSourceMap: false, // 生产环境是否生成 SourceMap
	devServer: {
			open: true, // 启动服务后是否打开浏览器
			host: '127.0.0.1',
			https: false,
			hotOnly: false,
			proxy: {
					'/': {
							target: 'http://127.0.0.1:8048',
							changeOrigin: true,  //允许跨域
							pathRewrite: null,
					},

			}, // 设置代理 测试使用
			before: app => {}
	},
	configureWebpack: {
	}

}
