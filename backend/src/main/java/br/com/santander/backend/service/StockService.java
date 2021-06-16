package br.com.santander.backend.service;

import br.com.santander.backend.dto.StockDTO;
import br.com.santander.backend.exceptions.BusinessException;
import br.com.santander.backend.mapper.StockMapper;
import br.com.santander.backend.model.Stock;
import br.com.santander.backend.repository.StockRepository;
import br.com.santander.backend.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository repository; // não sabe quem é dto, somente stock (bd)

    @Autowired
    private StockMapper mapper;

    @Transactional // a partir daqui iniciou a transação de BD | abre e fecha | begin, commit, rollback
    public StockDTO save(StockDTO dto) {
        // procura se tem stock com nome e dia já cadastrado
        Optional<Stock> optional = repository.findByNameAndDate(dto.getName(), dto.getDate());
        if (optional.isPresent()) {
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }
        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDTo(stock);
    }
}
