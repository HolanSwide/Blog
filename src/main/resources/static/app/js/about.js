new Vue({
    el: '#list',
    data: {
        username: '',
        uid: '',
        sign: '',
        email: '',
        follow: [
            { username: "test1", uid: "1001" },
            { username: "test2", uid: "1002" }
        ],
        follower: [
            { username: "test3", uid: "1003" },
            { username: "test4", uid: "1004" }
        ]
    },
    created() {
        axios
            .get('/me')
            .then(response => {
                this.username = response.data["user"]["username"];
                this.uid = response.data["user"]["uid"];
                this.sign = response.data["info"]["sign"];
                this.email = response.data["info"]["email"];

                axios.get('/usr/follow?uid=' + this.uid)
                    .then(response => {
                        this.follow = response.data["follow"];
                    });
                axios.get('/usr/follower?uid=' + this.uid)
                    .then(response => {
                        this.follower = response.data["follower"];
                    });
            });
    },
    methods: {
        // 取消关注
        cancelFollow(cancelFollowUid) {
            axios.delete("/usr/follow", {
                params: {
                    uid: this.uid,
                    followUid: cancelFollowUid
                }
            })
                .then(res => {
                    location.reload();
                })
                .catch(err => {
                    console.error(err);
                });
            location.reload();
        }
    }
})