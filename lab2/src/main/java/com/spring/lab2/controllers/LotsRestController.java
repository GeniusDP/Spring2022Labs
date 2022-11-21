package com.spring.lab2.controllers;

import com.spring.lab2.dto.request.LotCreationDto;
import com.spring.lab2.dto.request.LotUpdateDto;
import com.spring.lab2.dto.request.PageDto;
import com.spring.lab2.entities.Lot;
import com.spring.lab2.services.LotRestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.spring.lab2.Constants.Informer.*;
import static com.spring.lab2.Constants.TodoController.*;

@RestController
@RequestMapping("/api/lots")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = TAG, description = TAG_DESCRIPTION)
public class LotsRestController {
    private final LotRestService lotRestService;

    @Operation(summary = GET_ALL_LOTS_SUMMARY, description = GET_ALL_LOTS_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_STATUS_OK, description = "All lots")
    @GetMapping
    public List<Lot> getAllLots() {
        return lotRestService.findAll();
    }

    @Operation(summary = GET_PAGINATED_LOTS_LIST_SUMMARY, description = GET_PAGINATED_LOTS_LIST_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_STATUS_OK, description = "All lots")
    @Parameter(name = "page", required = true, description = "page number", in = ParameterIn.QUERY)
    @Parameter(name = "size", required = true, description = "page size", in = ParameterIn.QUERY)
    @GetMapping("/paginated")
    public List<Lot> getAllLotsPaginated(PageDto dto) {
        return lotRestService.findAllPaginated(dto);
    }

    @Operation(summary = GET_LOT_BY_ID_SUMMARY, description = GET_LOT_BY_ID_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_STATUS_OK, description = "Lot with such id is found")
    @ApiResponse(responseCode = HTTP_STATUS_NOT_FOUND, description = "Lot with such id not found")
    @Parameter(name = "lotId", required = true, description = "lot id", in = ParameterIn.PATH)
    @GetMapping("/{lotId}")
    public Lot getLotById(@PathVariable Integer lotId) {
        return lotRestService.findById(lotId);
    }

    @Operation(summary = SAVE_LOT_ITEM_SUMMARY, description = SAVE_LOT_ITEM_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_STATUS_CREATED, description = "New lot created")
    @ApiResponse(responseCode = HTTP_STATUS_NOT_FOUND, description = "No such user found")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Lot createLot(@Valid @RequestBody LotCreationDto dto) {
        return lotRestService.save(dto);
    }

    @Operation(summary = UPDATE_LOT_BY_ID_SUMMARY, description = UPDATE_LOT_BY_ID_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_STATUS_OK, description = "Lot is updated successfully")
    @ApiResponse(responseCode = HTTP_STATUS_NOT_FOUND, description = "Lot with such id not found")
    @ApiResponse(responseCode = HTTP_STATUS_BAD_REQUEST, description = "Cannot update a lot with the SOLD status")
    @PutMapping("/{lot-id}")
    public Lot updateLot(@PathVariable("lot-id") Integer lotId, @Valid @RequestBody LotUpdateDto dto) {
        return lotRestService.update(lotId, dto);
    }

    @Operation(summary = DELETE_LOT_BY_ID_SUMMARY, description = DELETE_LOT_BY_ID_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_STATUS_NO_CONTENT, description = "Deleted lot successfully")
    @ApiResponse(responseCode = HTTP_STATUS_NOT_FOUND, description = "Lot with such id not found")
    @Parameter(name = "lotId", required = true, description = "lot id", in = ParameterIn.PATH)
    @DeleteMapping("/{lotId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLot(@PathVariable Integer lotId) {
        lotRestService.deleteById(lotId);
    }

}
