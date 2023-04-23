package com.flower.spirit.web;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flower.spirit.common.AjaxEntity;
import com.flower.spirit.config.Global;
import com.flower.spirit.service.AnalysisService;

/**
 * api 调用控制器 此处控制器不拦截  仅通过token 校验
 * @author flower
 *
 */
@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private AnalysisService analysisService;
	
	private ExecutorService exec = Executors.newFixedThreadPool(1);
	
	
	/**
	 * 接受 视频平台的分享链接
	 * @param token
	 * @param video
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/processingVideos")
	public AjaxEntity processingVideos(String token,String video) throws Exception {
//		 analysisService.processingVideos(token,video);
		exec.execute(() -> {
			try {
				 analysisService.processingVideos(token,video);
			}catch (Exception e) {
				
			}
		});
		return new AjaxEntity(Global.ajax_success, "已提交,等待系统处理", "");
	
	}

}
