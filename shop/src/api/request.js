import axios from 'axios'
const service = axios.create({
    baseURL: "",
    timeout: 10000,
    // headers: {
    //     "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
    // }
});
axios.defaults.headers['Content-Type'] = 'application/json'
service.interceptors.request.use(
    (config) => {
        if (config.method === "post") {
            let curPost = config.url.split("/")[config.url.split("/").length - 1];
            if (curPost === "uploadpicture" || curPost === "fileUpload") {
                return config; // 这里对上传文件/图片的 api 不做传参序列化处理
            }
            // else {
            //     config.data = qs.stringify(config.data);
            //     return config;
            // }
        }
        return config;
    },
    (error) => {
        return Promise.reject();
    }
);
service.interceptors.response.use(response => {
    //接收到响应数据并成功后的一些共有的处理，关闭loading等
    if (response.status === 200) {
        return response.data;
    } else {
        console.log(response);
        Promise.reject();
    }
}, error => {
    /***** 接收到异常响应的处理开始 *****/
    if (error && error.response) {
        // 1.公共错误处理
        // 2.根据响应码具体处理
        switch (error.response.status) {
            case 400:
                console.log= '错误请求'
                break;
            case 401:
                console.log = '未授权，请重新登录'
                break;
            case 403:
                console.log = '拒绝访问'
                break;
            case 404:
                console.log = '请求错误,未找到该资源'
                //window.location.href = "/NotFound"
                break;
            case 405:
                console.log = '请求方法未允许'
                break;
            case 408:
                console.log = '请求超时'
                break;
            case 500:
                console.log = '服务器端出错'
                break;
            case 501:
                error.message = '网络未实现'
                break;
            case 502:
                console.log = '网络错误'
                break;
            case 503:
                console.log = '服务不可用'
                break;
            case 504:
                console.log = '网络超时'
                break;
            case 505:
                console.log = 'http版本不支持该请求'
                break;
            default:
                console.log = `连接错误${error.response.status}`
        }
    } else {
        // 超时处理
        // if (qs.stringify(error).includes('timeout')) {
        //     console.log('服务器响应超时，请刷新当前页')
        // }
        // console.log('连接服务器失败')
    }

    //console.log(error.message)
    /***** 处理结束 *****/
    //如果不需要错误处理，以上的处理过程都可省略
    return Promise.resolve(error.response)
})

export default service;
