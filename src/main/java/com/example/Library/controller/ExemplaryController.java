package com.example.Library.controller;

import com.example.Library.dto.CreatedExemplaryDto;
import com.example.Library.dto.ExemplaryDto;
import com.example.Library.dto.validation.ValidationOrder;
import com.example.Library.entities.Exemplary;
import com.example.Library.mapper.ExemplaryMapper;
import com.example.Library.service.ExemplaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exemplars")
public class ExemplaryController {
    @Autowired
    private ExemplaryService exemplaryService;

    @PostMapping
    public ResponseEntity<List<ExemplaryDto>> createExemplars(@RequestBody @Validated(ValidationOrder.class) CreatedExemplaryDto createdExemplaryDto) {
        List<Exemplary> exemplars = exemplaryService.create(
                createdExemplaryDto.getPublisher(),
                createdExemplaryDto.getMaxReservationDays(),
                createdExemplaryDto.getBookId(),
                createdExemplaryDto.getCount()
        );

        List<ExemplaryDto> exemplarsDto = ExemplaryMapper.toDtoList(exemplars);

        return ResponseEntity.ok(exemplarsDto);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<ExemplaryDto>> getExemplarsByBook(
            @PathVariable Long bookId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {

        List<ExemplaryDto> exemplars = exemplaryService.findByBookId(bookId, page, size);
        return ResponseEntity.ok(exemplars);
    }

    @DeleteMapping("/{exemplaryId}")
    public ResponseEntity<?> deleteExemplary(@PathVariable Long exemplaryId) {
        exemplaryService.delete(exemplaryId);
        return ResponseEntity.ok().build();
    }
}
