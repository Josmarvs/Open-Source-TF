package com.appreservas.Controller;

import com.appreservas.Entity.Comment;
import com.appreservas.Entity.Beginner;
import com.appreservas.Entity.Chef;
import com.appreservas.Service.ICommentService;
import com.appreservas.Service.IBeginnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
@Api(tags = "Ingredient", value = "Service Web RESTFul de Ingredient")
public class IngredientController {

    @Autowired
    private IIngredientService ingredientService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Ingredients",notes = "Método para listar todos los Ingredients")
    @ApiResponses({
            @ApiResponse(code = 201,message = "Ingredients encontrados"),
            @ApiResponse(code = 404,message = "Ingredients no encontrados")
    })
    public ResponseEntity<List<Ingredient>>findAllIngredient(){
        try {
            List<Ingredient> ingredient = ingredientService.getAll();
            if(ingredients.size()>0)
                return new ResponseEntity<List<Ingredient>>(ingredients,HttpStatus.OK);
            else
                return new ResponseEntity<List<Ingredient>>(HttpStatus.NO_CONTENT);

        }catch (Exception e){
            return new ResponseEntity<List<Ingredient>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Ingredient por Id",notes = "Método para listar un Ingredient por Id")
    @ApiResponses({
            @ApiResponse(code = 201,message = "Ingredient encontrados"),
            @ApiResponse(code = 404,message = "Ingredient no encontrados")
    })
    public ResponseEntity<Ingredient>findIngredientById(@PathVariable("id")Long id){
        try{
            Optional<Ingredient>ingredient=ingredientService.getById(id);
            if(!ingredient.isPresent())
                return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<Ingredient>(ingredient.get(),HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<Ingredient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/searchByDate",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Ingredient entre fechas",notes = "Método para listar un Ingredient entre fechas")
    @ApiResponses({
            @ApiResponse(code = 201,message = "Ingredient encontrados"),
            @ApiResponse(code = 404,message = "Ingredient no encontrados")
    })
    public ResponseEntity<List<Ingredient>> findIngredientByDate(@RequestParam("publication_Date") String publication_string){
        try {
            Date publication_Date=ParseDate(publication_string);
            List<Ingredient> ingredients =ingredientService.find(publication_Date);
            if (ingredient.size()>0)
                return new ResponseEntity<List<Ingredient>>(ingredients,HttpStatus.OK);
            else
                return new ResponseEntity<List<Ingredient>>(HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<List<Ingredient>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static Date ParseDate(String date){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date result = null;
        try{
            result = format.parse(date);
        }catch (Exception e){

        }
        return result;
    }


    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de Ingredient",notes = "Metodo que actualiza los datos de Ingredient")
    @ApiResponses({
            @ApiResponse(code=201, message = "Datos de Ingredient actualizados"),
            @ApiResponse(code=404,message = "Ingredient no encontrado")
    })
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable("id")Long id, @Valid @RequestBody Ingredient ingredient){
        try {
            Optional<Ingredient>ingredientOld=ingredientService.getById(id);
            if(!ingredientOld.isPresent())
                return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
            else{
                ingredient.setId(id);
                ingredientService.save(ingredient);
                return new ResponseEntity<Ingredient>(ingredient,HttpStatus.OK);
            }

        }catch (Exception e){
            return new ResponseEntity<Ingredient>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de Ingredient por Id",notes = "Metodo que elimina los datos de un Ingredient")
    @ApiResponses({
            @ApiResponse(code=201, message = "Datos de Ingredient eliminados"),
            @ApiResponse(code=404,message = "Ingredient no encontrado")
    })
    public ResponseEntity<Ingredient> deleteIngredient(@PathVariable("id")Long id){
        try {
            Optional<Ingredient> ingredientDelete=ingredientService.getById(id);
            if(ingredientDelete.isPresent()){
                ingredientService.delete(id);
                return new ResponseEntity<Ingredient>(HttpStatus.OK);
            }
            else
                return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<Ingredient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
