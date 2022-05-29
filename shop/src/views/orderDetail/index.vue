<template>
    <div id="orderDetail">
        <el-timeline :reverse="false">
            <el-timeline-item
            v-for="(activity, index) in activities"
            :key="index"
            :timestamp="activity.timestamp">
            {{activity.content}}
            </el-timeline-item>
        </el-timeline>
    </div>
</template>
<script>
export default {
    name:'orderDetail',
    data(){
        return{
            activities: [{
                content: '活动按期开始',
                timestamp: '2018-04-15'
            }, {
                content: '通过审核',
                timestamp: '2018-04-13'
            }, {
                content: '创建成功',
                timestamp: '2018-04-11'
            }]
        }
    }
}
</script>