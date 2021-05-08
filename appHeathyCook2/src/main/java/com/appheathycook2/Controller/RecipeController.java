package com.appheathycook2.Controller;

import com.appheathycook2.Entity.Chef;
import com.appheathycook2.Entity.Recipe;
import com.appheathycook2.Service.IChefService;
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

@RestController
@RequestMapping("/api/recipes")
@Api(tags = "Recipe", value = "Service Web RESTFul de Comments")
public class RecipeController {

    @Autowired
    private IChefService chefService;

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
    public ResponseEntity<Recipe> findRecipeById(@PathVariable("id") Long id) {
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

    @GetMapping(value = "/searchByDate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Recipe entre fechas", notes = "Método para listar un Recipe entre fechas")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Recipe encontrados"),
            @ApiResponse(code = 404, message = "Recipe no encontrados")
    })
    public ResponseEntity<List<Recipe>> findRecipeByDate(@RequestParam("publication_Date") String publication_string) {
        try {
            Date publication_Date = ParseDate(publication_string);
            List<Recipe> recipes = recipeService.find(publication_Date);
            if (recipes.size() > 0)
                return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
            else
                return new ResponseEntity<List<Recipe>>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<List<Recipe>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static Date ParseDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date result = null;
        try {
            result = format.parse(date);
        } catch (Exception e) {

        }
        return result;
    }


    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de un Recipe", notes = "Método que registra un Recipe")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Recipe creado"),
            @ApiResponse(code = 404, message = "Recipe no creado")
    })
    public ResponseEntity<Recipe> insertRecipeChef(@PathVariable("id") Long id, @Valid @RequestBody Recipe recipe) {
        try {
            Optional<Chef> chef = chefService.getById(id);
            if (chef.isPresent()) {
                recipe.setChef(chef.get());
                Recipe recipeNew = recipeService.save(recipe);
                return ResponseEntity.status(HttpStatus.CREATED).body(recipeNew);
            } else
                return new ResponseEntity<Recipe>(HttpStatus.FAILED_DEPENDENCY);
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
    @ApiOperation(value = "Eliminación de Recipe por Id", notes = "Metodo que elimina los datos de un Recipe")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Datos de Recipe eliminados"),
            @ApiResponse(code = 404, message = "Recipe no encontrado")
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
