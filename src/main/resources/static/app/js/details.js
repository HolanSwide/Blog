new Vue({
    el:'#essay',
    data:{
        e:{title:'',time:'',uid:'',fid:''},
        postInfo:{
            key:'pid',
            value:'',
            begin:'0',
            end:'1'
        }
    },
    created(){
        this.postInfo.value = location.href.slice(location.href.indexOf('?')+1)
        axios({
            method: 'post',
            url: '/essay',
            data: JSON.stringify(this.postInfo),
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            }
        }).then(res=>{
            this.e=res.data["data"][0];
        })
    },
    watch: {
        '$route.path'(now,old){
            // now: 变化之后的数据
            // old: 变化之前的数据
            console.log(now,old)
        }
    }
});