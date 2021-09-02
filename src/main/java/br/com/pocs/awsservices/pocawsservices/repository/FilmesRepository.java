package br.com.pocs.awsservices.pocawsservices.repository;

import br.com.pocs.awsservices.pocawsservices.model.Filme;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FilmesRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public Optional<Filme> getFilmeById(String id){
        return Optional.ofNullable(dynamoDBMapper.load(Filme.class, id));
    }

    public Filme save(Filme filme) {
        dynamoDBMapper.save(filme);
        return filme;
    }

    public Filme update(Filme filme) {
        dynamoDBMapper.save(filme,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("id",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(filme.getId())
                                )));
        return filme;
    }

    public void delete(String id) {
        final var filme = dynamoDBMapper.load(Filme.class, id);
        dynamoDBMapper.delete(filme);
    }
}
