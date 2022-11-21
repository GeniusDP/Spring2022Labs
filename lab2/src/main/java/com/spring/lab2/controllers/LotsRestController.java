package com.spring.lab2.controllers;

import static com.spring.lab2.Constants.Informer.HTTP_STATUS_NOT_FOUND;
import static com.spring.lab2.Constants.Informer.HTTP_STATUS_OK;
import static com.spring.lab2.Constants.TodoController.GET_ALL_LOTS_DESCRIPTION;
import static com.spring.lab2.Constants.TodoController.GET_ALL_LOTS_SUMMARY;
import static com.spring.lab2.Constants.TodoController.GET_LOT_BY_ID_DESCRIPTION;
import static com.spring.lab2.Constants.TodoController.GET_LOT_BY_ID_SUMMARY;
import static com.spring.lab2.Constants.TodoController.TAG;
import static com.spring.lab2.Constants.TodoController.TAG_DESCRIPTION;

import com.spring.lab2.entities.Lot;
import com.spring.lab2.services.LotRestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lots")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = TAG, description = TAG_DESCRIPTION)
public class LotsRestController {
  private final LotRestService lotRestService;

  @Operation(summary = GET_ALL_LOTS_SUMMARY, description = GET_ALL_LOTS_DESCRIPTION)
  @ApiResponse(responseCode = HTTP_STATUS_OK, description = "All lots")
  @GetMapping({"/", ""})
  public List<Lot> getAllLots(){
    return lotRestService.findAll();
  }

  @Operation(summary = GET_LOT_BY_ID_SUMMARY, description = GET_LOT_BY_ID_DESCRIPTION)
  @ApiResponse(responseCode = HTTP_STATUS_OK, description = "Lot with such id is found")
  @ApiResponse(responseCode = HTTP_STATUS_NOT_FOUND, description = "Lot with such id not found")
  @GetMapping("/{lotId}")
  public Lot getLotById(@PathVariable Integer lotId){
    return lotRestService.findById(lotId);
  }
  
}
