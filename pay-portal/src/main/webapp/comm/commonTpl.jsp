<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- AppCode tpl-->
<script type="text/html" id="appCodeTpl">
    {{# if(d.appCode == 'HM_PARTNER'){ }}
    <span>好卖</span>
	{{# }else if(d.appCode == 'KY_PARTNER'){}}
	 <span>卡友</span>
	{{# } }}
</script>

<!-- 状态tpl-->
<script type="text/html" id="noticeStatusTpl">
    {{# if(d.status == 'ENABLE'){ }}
    <span class="label label-success ">有效</span>
    {{# }else if(d.status == 'DISABLE'){ }}
    <span class="label label-danger ">失效</span>
	{{# }}}
</script>

