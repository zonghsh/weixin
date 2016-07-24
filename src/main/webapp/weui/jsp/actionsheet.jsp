<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="hd">
    <h1 class="page_title">ActionSheet</h1>
</div>
<div class="bd spacing">
    <a href="javascript:;" class="weui_btn weui_btn_primary" id="showActionSheet">点击上拉ActionSheet</a>
</div>
<!--BEGIN actionSheet-->
<div id="actionSheet_wrap">
    <div class="weui_mask_transition" id="mask"></div>
    <div class="weui_actionsheet" id="weui_actionsheet">
        <div class="weui_actionsheet_menu">
            <div class="weui_actionsheet_cell">示例菜单</div>
            <div class="weui_actionsheet_cell">示例菜单</div>
            <div class="weui_actionsheet_cell">示例菜单</div>
            <div class="weui_actionsheet_cell">示例菜单</div>
        </div>
        <div class="weui_actionsheet_action">
            <div class="weui_actionsheet_cell" id="actionsheet_cancel">取消</div>
        </div>
    </div>
</div>
<!--END actionSheet-->