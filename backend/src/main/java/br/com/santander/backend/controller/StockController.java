package br.com.santander.backend.controller;

import br.com.santander.backend.dto.StockDTO;
import br.com.santander.backend.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/stock")
public class StockController {

    @Autowired
    private StockService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> save (@Valid @RequestBody StockDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> update(@Valid @RequestBody StockDTO dto) {
        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockDTO>> findAll() {
        List<StockDTO> list = new ArrayList<>();
        StockDTO dto = new StockDTO();
        dto.setId(1L);
        dto.setName("Magazine Luiza");
        dto.setPrice(100D);
        dto.setVariation(10D);
        dto.setDate(LocalDate.now());
        list.add(dto);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> findById(@PathVariable Long id) {
        List<StockDTO> list = new ArrayList<>();
        StockDTO stock1 = new StockDTO();
        stock1.setId(1L);
        stock1.setName("Magazine Luiza");
        stock1.setPrice(100D);
        stock1.setVariation(10D);
        stock1.setDate(LocalDate.now());

        StockDTO stock2 = new StockDTO();
        stock2.setId(2L);
        stock2.setName("Casas Bahia");
        stock2.setPrice(200D);
        stock2.setVariation(5D);
        stock2.setDate(LocalDate.now());;

        list.add(stock1);
        list.add(stock2);

        StockDTO selectedStock = list.stream()
            .filter(stock -> stock.getId().compareTo(id) == 0) // eu quero o igual
            .findFirst().get();

        return ResponseEntity.ok(selectedStock);
    }
}
