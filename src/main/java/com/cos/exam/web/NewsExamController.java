package com.cos.exam.web;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.exam.domain.NewsExam;
import com.cos.exam.domain.NewsExamRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RequiredArgsConstructor
@RestController
public class NewsExamController {

	private final NewsExamRepository newsExamRepository;
	
	@CrossOrigin // 서버는 다른도메인의 자바스크립트 요청을 거부한다. (허용해주는 어노테이션)
	@GetMapping(value = "/news" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<NewsExam> findAll(){

		return newsExamRepository.mFindAll()
				.subscribeOn(Schedulers.boundedElastic() );
	}
	
	@PostMapping("/news")
	public Mono<NewsExam> save(@RequestBody NewsExam newsExam){
		return newsExamRepository.save(newsExam);
	}

}
