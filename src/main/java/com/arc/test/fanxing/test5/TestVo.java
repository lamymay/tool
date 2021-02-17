package com.arc.test.fanxing.test5;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Getter
@Setter
@ToString
public class TestVo<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态信息 code 用String或者数字类型（long/int？）。私以为：类型是数字类型 可能高效一些，
     */
    private int code;

    /**
     * 状态信息
     */
    private String msg;


    /**
     * 有效数据
     */
    private T data;

    //构造器
    public TestVo() {
    }

    public TestVo(T data) {
        this.data = data;
    }


    public TestVo(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //success方法
    public static <T> TestVo<T> success() {
        return new TestVo<T>(ProjectCodeEnum.SUCCESS.getCode(), ProjectCodeEnum.SUCCESS.getMsg(), null);
    }

    public static <T> TestVo<T> success(T data) {
        return new TestVo<T>(ProjectCodeEnum.SUCCESS.getCode(), ProjectCodeEnum.SUCCESS.getMsg(), data);
    }

    public static <T> TestVo<T> success(ProjectCodeEnum enumCode) {
        return new TestVo<T>(enumCode.getCode(), enumCode.getMsg(), null);
    }

    public static <T> TestVo<T> success(ProjectCodeEnum enumCode, T data) {
        return new TestVo<T>(enumCode.getCode(), enumCode.getMsg(), data);
    }

    //失败
    public static <T> TestVo<T> failure(ProjectCodeEnum enumCode) {
        return new TestVo<T>(enumCode.getCode(), enumCode.getMsg(), null);
    }

    public static <T> TestVo<T> failure(T data) {
        return new TestVo<T>(ProjectCodeEnum.FAILURE.getCode(), ProjectCodeEnum.FAILURE.getMsg(), data);
    }

    public static <T> TestVo<T> failure(ProjectCodeEnum enumCode, T data) {
        return new TestVo<T>(enumCode.getCode(), enumCode.getMsg(), data);
    }

    public static TestVo failure() {
        return new TestVo(ProjectCodeEnum.FAILURE);
    }

    public static TestVo failure(int code, String msg) {
        return new TestVo(code, msg, ProjectCodeEnum.FAILURE);
    }


    /**
     * @param data
     * @return
     */
    public static <T> TestVo<T> page(T data) {

        TestVo responseVo = new TestVo();

        responseVo.setData(data);
        Page page = new Page();
        responseVo.setPage(page);

        //当前页
        page.setCurrentPage(1);

        //页面容量=一页显示多少
        page.setPageSize(10);

        //总页数
        page.setTotalCount(100);


        //	public Integer getStart(){
        //		return (this.page-1)*this.rows;
        //	}
        return responseVo;
    }

    private Page page;

    public static class Page implements Serializable {
        private static final long serialVersionUID = -9015310768471855060L;
        private Integer currentPage;
        private Integer pageSize;
        private Integer totalCount;
        private Integer totalPage;

        private Page() {
            this.pageSize = 10;
            this.totalPage = 0;
        }

        private Page(Integer currentPage, Integer pageSize, Integer totalCount) {
            this.pageSize = 10;
            this.totalPage = 0;
            this.currentPage = currentPage;
            this.pageSize = pageSize;
            this.totalCount = totalCount;
        }

        public void setCurrentPage(Integer currentPage) {
            this.currentPage = currentPage;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

        public void setTotalPage(Integer totalPage) {
            this.totalPage = totalPage;
        }

        public Integer getCurrentPage() {
            return this.currentPage;
        }

        public Integer getPageSize() {
            return this.pageSize;
        }

        public Integer getTotalCount() {
            return this.totalCount;
        }

        public Integer getTotalPage() {
            if (this.pageSize != null && this.pageSize > 0) {
                if (this.totalCount % this.pageSize == 0) {
                    this.totalPage = this.totalCount / this.pageSize;
                } else {
                    this.totalPage = this.totalCount / this.pageSize + 1;
                }
            }

            return this.totalPage;
        }

    }


}
