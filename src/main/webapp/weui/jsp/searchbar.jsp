<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="hd">
    <h1 class="page_title">searchBar</h1>
</div>
<div class="bd">
    <!--<a href="javascript:;" class="weui_btn weui_btn_primary">点击展现searchBar</a>-->
    <div class="weui_search_bar" id="search_bar">
        <form class="weui_search_outer">
            <div class="weui_search_inner">
                <i class="weui_icon_search"></i>
                <input type="search" class="weui_search_input" id="search_input" placeholder="搜索" required/>
                <a href="javascript:" class="weui_icon_clear" id="search_clear"></a>
            </div>
            <label for="search_input" class="weui_search_text" id="search_text">
                <i class="weui_icon_search"></i>
                <span>搜索</span>
            </label>
        </form>
        <a href="javascript:" class="weui_search_cancel" id="search_cancel">取消</a>
    </div>
    <div class="weui_cells weui_cells_access search_show" id="search_show">
        <div class="weui_cell">
            <div class="weui_cell_bd weui_cell_primary">
                <p>实时搜索文本</p>
            </div>
        </div>
        <div class="weui_cell">
            <div class="weui_cell_bd weui_cell_primary">
                <p>实时搜索文本</p>
            </div>
        </div>
        <div class="weui_cell">
            <div class="weui_cell_bd weui_cell_primary">
                <p>实时搜索文本</p>
            </div>
        </div>
        <div class="weui_cell">
            <div class="weui_cell_bd weui_cell_primary">
                <p>实时搜索文本</p>
            </div>
        </div>
    </div>
</div>