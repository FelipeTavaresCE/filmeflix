package com.example.filmeflix.controller;

import com.example.filmeflix.dto.FilmeDTO;
import com.example.filmeflix.dto.FilmeResponse;
import com.example.filmeflix.service.FilmeService;
import com.example.filmeflix.util.FilmeUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Tag(name = "Movies API", description = "")
@RestController
@RequestMapping("/v1/filmes")
@RequiredArgsConstructor
public class FilmeController {
	
	private final FilmeService service;
	
	@Operation(summary = "Create a new movie")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Ok.", content = @Content), 
		@ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content),
		@ApiResponse(responseCode = "500", description = "Internal Error", content = @Content),
		})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FilmeResponse> saveMovie(@Valid @Parameter(required = true) @RequestBody FilmeDTO request){
		return ResponseEntity.ok(FilmeUtil.parseFilmeIntoFilmeResponse(service.save(request)));
	}
	
	@Operation(summary = "Delete a old movie")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Ok.", content = @Content), 
		@ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content),
		@ApiResponse(responseCode = "404", description = "Movie Not Found.", content = @Content),
		@ApiResponse(responseCode = "500", description = "Internal Error", content = @Content),
		})
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteMovie(@Parameter(required = true) @PathVariable("id") String id){
		service.delete(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@Operation(summary = "Find new list movie")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Ok.", content = @Content),
		@ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content),
		@ApiResponse(responseCode = "404", description = "Movie Not Found.", content = @Content),
		@ApiResponse(responseCode = "500", description = "Internal Error", content = @Content),
		})
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FilmeResponse>> getMovies(){
		return ResponseEntity.ok(service.findLatestMovies().stream().map(FilmeUtil::parseFilmeIntoFilmeResponse).collect(Collectors.toList()));
	}

}
