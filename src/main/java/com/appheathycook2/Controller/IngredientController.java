package com.appheathycook2.Controller;

import com.appheathycook2.Entity.Ingredient;
import com.appheathycook2.Service.IIngredientService;
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
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/ingredients")
@Api(tags = "Ingredient", value = "Service Web RESTFul de Ingredients")

public class IngredientController {

    @Autowired
    private IIngredientService ingredientService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Ingredients", notes = "Método para listar todos los Ingredients")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ingredients encontrados"),
            @ApiResponse(code = 404, message = "Ingredients no encontrados")
    })
    public ResponseEntity<List<Ingredient>> findAllIngredient() {
        try {
            List<Ingredient> ingredients = ingredientService.getAll();
            if (ingredients.size() > 0)
                return new ResponseEntity<List<Ingredient>>(ingredients, HttpStatus.OK);
            else
                return new ResponseEntity<List<Ingredient>>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<List<Ingredient>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Ingredient por Id", notes = "Método para listar un Ingredient por Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ingredient encontrados"),
            @ApiResponse(code = 404, message = "Ingredient no encontrados")
    })
    public ResponseEntity<Ingredient> findIngredientById(@PathVariable("id") Long id) {
        try {
            Optional<Ingredient> ingredient = ingredientService.getById(id);
            if (!ingredient.isPresent())
                return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Ingredient>(ingredient.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Ingredient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("searchByIngredientName/{name}")
    @ApiOperation(value = "Buscar Ingredient por name", notes = "Métodos para encontrar un Ingredient por su respectivo name")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ingredient encontrados"),
            @ApiResponse(code = 404, message = "Ingredient no encontrados")
    })
    public ResponseEntity<List<Ingredient>> findByIngredientName(@PathVariable("name") String name) {
        try {
            List<Ingredient> ingredients = ingredientService.findByName(name);
            if (ingredients.size() > 0)
                return new ResponseEntity<List<Ingredient>>(ingredients, HttpStatus.OK);
            else
                return new ResponseEntity<List<Ingredient>>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<List<Ingredient>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registrar ingredientes", notes = "Metodo para registrar ingredientes")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ingrediente encontrado"),
            @ApiResponse(code = 201, message = "Ingrediente no encontrado")
    })
    public ResponseEntity<Ingredient> insertIngredient(@Valid @RequestBody Ingredient ingredient) {
        try {
            Ingredient ingredientNew = ingredientService.save(ingredient);
            return ResponseEntity.status(HttpStatus.CREATED).body(ingredientNew);
        } catch (Exception e) {
            return new ResponseEntity<Ingredient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de Ingredient", notes = "Metodo que actualiza los datos de Ingredient")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Datos de Ingredient actualizados"),
            @ApiResponse(code = 404, message = "Ingredient no encontrado")
    })
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable("id") Long id, @Valid @RequestBody Ingredient ingredient) {
        try {
            Optional<Ingredient> ingredientOld = ingredientService.getById(id);
            if (!ingredientOld.isPresent())
                return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
            ingredient.setId(id);
            ingredientService.save(ingredient);
            return new ResponseEntity<Ingredient>(ingredient, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Ingredient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de Ingredient por Id", notes = "Metodo que elimina los datos de un Ingredient")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Datos de Ingredient eliminados"),
            @ApiResponse(code = 404, message = "Ingredient no encontrado")
    })
    public ResponseEntity<Ingredient> deleteIngredient(@PathVariable("id") Long id) {
        try {
            Optional<Ingredient> ingredientDelete = ingredientService.getById(id);
            if (!ingredientDelete.isPresent())
                return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
            ingredientService.delete(id);
            return new ResponseEntity<Ingredient>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Ingredient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
