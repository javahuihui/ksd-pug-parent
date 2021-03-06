Vue.component("simple-page", {
    //通过props来接受从父组件传递过来的值
    props: {
        //页面中的可见页码，其他的以...替代, 必须是奇数
        perPages: {
            type: Number,
            default: 5
        },

        //当前页码
        pageIndex: {
            type: Number,
            default: 1
        },

        //每页显示条数
        pageSize: {
            type: Number,
            default: 10
        },

        //总记录数
        total: {
            type: Number,
            default: 1
        },

    },

    template:`<div class="page-number">
        <ul class="page-list">
            <li :class="['page-li','prev', {'disabled' : index === 1}]" @click="prev"><span class="page-text">上一页</span></li>
            <li :class="['page-li','page-li-first', {'disabled' : index === 1}]"  @click="first"><span class="page-text">首页</span></li>
            <li class="page-li more btn-quicknext" v-if="showPrevMore"><span class="page-text more btn-quicknext">...</span></li>
            <li :class="['page-li', {'active' : index === pager}]" v-for="pager in pagers" @click="go(pager)"><span class="page-text">{{pager}}</span></li>
            <li class="page-li more btn-quicknext" v-if="showPrevMore"><span class="page-text more btn-quicknext">...</span></li>
            <li :class="['page-li','page-li-last',  {'disabled' : index === pages}]" @click="last">尾页</li>
            <li :class="['page-li','next',  {'disabled' : index === pages}]" @click="next">下一页</li>
             <li class="page-li"><span class="page-text">共{{total}}/{{pages}}页</span></li>
        </ul>
    </div>`,

    methods: {
        prev() {
            if(this.index > 1) {
                this.go(this.index - 1)
            }
        },
        next() {
            if(this.index < this.pages) {
                this.go(this.index + 1)
            }
        },
        first() {
            if(this.index !== 1) {
                this.go(1)
            }
        },
        last() {
            if(this.index != this.pages) {
                this.go(this.pages)
            }
        },
        go(page) {
            if(this.index !== page) {
                this.index = page
                //父组件通过change方法来接受当前的页码
                this.$emit('change', this.index)
            }
        }
    },
    computed: {

        //计算总页码
        pages() {
            return Math.ceil(this.size / this.limit)
        },

        //计算页码，当count等变化时自动计算
        pagers() {
            const array = []
            const perPages = this.perPages
            const pageCount = this.pages
            let current = this.index
            const _offset = (perPages - 1) / 2

            const offset = {
                start: current - _offset,
                end: current + _offset
            }

            //-1, 3
            if(offset.start < 1) {
                offset.end = offset.end + (1 - offset.start)
                offset.start = 1
            }
            if(offset.end > pageCount) {
                offset.start = offset.start - (offset.end - pageCount)
                offset.end = pageCount
            }
            if(offset.start < 1) offset.start = 1

            this.showPrevMore = (offset.start > 1)
            this.showNextMore = (offset.end < pageCount)

            for(let i = offset.start; i <= offset.end; i++) {
                array.push(i)
            }

            return array
        }
    },
    data() {
        return {
            index: this.pageIndex, //当前页码
            limit: this.pageSize, //每页显示条数
            size: this.total || 1, //总记录数
            showPrevMore: false,
            showNextMore: false
        }
    },
    watch: {
        pageIndex(val) {
            this.index = val || 1
        },
        pageSize(val) {
            this.limit = val || 10
        },
        total(val) {
            this.size = val || 1
        }
    }

});