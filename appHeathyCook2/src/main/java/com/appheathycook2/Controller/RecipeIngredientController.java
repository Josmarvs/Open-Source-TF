package com.appheathycook2.Controller;

import com.appheathycook2.Entity.Ingredient;
import com.appheathycook2.Entity.Recipe;
import com.appheathycook2.Service.IIngredientService;
import com.appheathycook2.Service.IRecipeService;
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

@CrossOrigin
@RestController
@RequestMapping("/api/recipeingredient")
@Api(tags = "Recipe", value = "Service Web RESTFul de Comments")
public class RecipeIngredientController {

    @Autowired
    private IIngredientService ingredientService;

    @Autowired
    private IRecipeService recipeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Recipes", notes = "Método para listar todos los Recipes")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Recipes encontrados"),
            @ApiResponse(code = 404, message = "Recipes no encontrados")
    })
    public ResponseEntity<List<Recipe>> findAllRecipe() {
        try {
            List<Recipe> recipes = recipeService.getAll();
            if (recipes.size() > 0)
                return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
            else
                return new ResponseEntity<List<Recipe>>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<List<Recipe>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Recipe por Id", notes = "Método para listar un Recipe por Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Recipe encontrados"),
            @ApiResponse(code = 404, message = "Recipe no encontrados")
    })
    public ResponseEntity<Recipe> findByQuantity(@PathVariable("id") Long id) {
        try {
            Optional<Recipe> recipe = recipeService.getById(id);
            if (!recipe.isPresent())
                return new ResponseEntity<Recipe>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<Recipe>(recipe.get(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Recipe>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de Recipe", notes = "Metodo que actualiza los datos de Recipe")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Datos de Recipe actualizados"),
            @ApiResponse(code = 404, message = "Recipe no encontrado")
    })
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") Long id, @Valid @RequestBody Recipe recipe) {
        try {
            Optional<Recipe> recipeOld = recipeService.getById(id);
            if (!recipeOld.isPresent())
                return new ResponseEntity<Recipe>(HttpStatus.NOT_FOUND);
            else {
                recipe.setId(id);
                recipeService.save(recipe);
                return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<Recipe>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de RecipeIngredient por Id", notes = "Metodo que elimina los datos de un RecipeIngredient")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Datos de RecipeIngredient eliminados"),
            @ApiResponse(code = 404, message = "RecipeIngredient no encontrado")
    })
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable("id") Long id) {
        try {
            Optional<Recipe> recipeDelete = recipeService.getById(id);
            if (recipeDelete.isPresent()) {
                recipeService.delete(id);
                return new ResponseEntity<Recipe>(HttpStatus.OK);
            } else
                return new ResponseEntity<Recipe>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<Recipe>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
