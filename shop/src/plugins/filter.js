//全局过滤器
export function formatNum(value){
    if(value!=null&&value!=undefined){
        return value.toFixed(2)
    }
    return '--'
}