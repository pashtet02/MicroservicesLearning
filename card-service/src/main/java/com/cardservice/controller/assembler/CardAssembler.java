package com.cardservice.controller.assembler;

import com.cardservice.controller.CardController;
import com.cardservice.controller.model.CardModel;
import com.cardservice.dto.CardDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CardAssembler extends RepresentationModelAssemblerSupport<CardDto, CardModel> {

    public CardAssembler() {
        super(CardController.class, CardModel.class);
    }

    @Override
    public CardModel toModel(CardDto entity) {
        CardModel cardModel = new CardModel(entity);

        Link get = linkTo(methodOn(CardController.class).getCard(entity.getCardId())).withRel("get");
        Link create = linkTo(methodOn(CardController.class).createCard(entity)).withRel("create");
        Link update = linkTo(methodOn(CardController.class).updateCard(entity.getCardId(), entity)).withRel("update");
       Link delete = linkTo(methodOn(CardController.class).deleteCard(entity.getCardId())).withRel("delete");

        cardModel.add(get, create, update, delete);
        //cardModel.add(create);
        return cardModel;
    }

}
