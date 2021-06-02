package com.appheathycook2.Controller;

import com.appheathycook2.Entity.Chef;
import com.appheathycook2.Entity.Recipe;
import com.appheathycook2.Entity.WeekRecipe;
import com.appheathycook2.Service.IRecipeService;
import com.appheathycook2.Repository.IWeekRecipeRepository;
import com.appheathycook2.Service.IWeekRecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/weekRecipes")
@Api(tags = "WeekRecipe", value = "Service Web RESTFul de WeekRecipes")
public class WeekRecipeController {

    @Autowired
    private IWeekRecipeRepository weekRecipeRepository;

    @Autowired
    private IWeekRecipeService weekRecipeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Recipes por semana", notes = "Método para listar todos los recipe por semana")
    @ApiResponses({
            @ApiResponse(code = 201, message = "weekRecipes encontrados"),
            @ApiResponse(code = 404, message = "weekRecipes no encontrados")
    })

    public ResponseEntity<List<WeekRecipe>> findAllWeekRecipe() {
        try {
            List<WeekRecipe> weekRecipes = weekRecipeService.getAll();
            if (weekRecipes.size() > 0)
                return new ResponseEntity<List<WeekRecipe>>(weekRecipes, HttpStatus.OK);
            else
                return new ResponseEntity<List<WeekRecipe>>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<List<WeekRecipe>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar WeekRecipe por Id", notes = "Método para listar un WeekRecipe por Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "WeekRecipe encontrados"),
            @ApiResponse(code = 404, message = "WeekRecipe no encontrados")
    })
    public ResponseEntity<WeekRecipe> findWeekRecipeById(@PathVariable("id") Long id) {
        try {
            Optional<WeekRecipe> weekRecipe = weekRecipeService.getById(id);
            if (!weekRecipe.isPresent())
                return new ResponseEntity<WeekRecipe>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<WeekRecipe>(weekRecipe.get(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<WeekRecipe>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/searchByDate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Recipe por semanas", notes = "Método para listar un Recipe por semanas")
    @ApiResponses({
            @ApiResponse(code = 201, message = "weekRecipe encontrados"),
            @ApiResponse(code = 404, message = "weekRecipe no encontrados")
    })
    public ResponseEntity<List<WeekRecipe>> findWeekRecipeByDate(@RequestParam("publication_Date") String publication_string) {
        try {
            Date publication_Date = ParseDate(publication_string);
            List<WeekRecipe> weekRecipes = weekRecipeService.find(publication_Date);
            if (weekRecipes.size() > 0)
                return new ResponseEntity<List<WeekRecipe>>(weekRecipes, HttpStatus.OK);
            else
                return new ResponseEntity<List<WeekRecipe>>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<List<WeekRecipe>>(HttpStatus.INTERNAL_SERVER_ERROR);
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


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de Recipes por semana", notes = "Metodo que elimina los datos semanales de recipes")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Datos de weekRecipe eliminados"),
            @ApiResponse(code = 404, message = "WeekRecipe no encontrado")
    })
    public ResponseEntity<WeekRecipe> deleteWeekRecipe(@PathVariable("id") Long id) {
        try {
            Optional<WeekRecipe> weekRecipeDelete = weekRecipeService.getById(id);
            if (weekRecipeDelete.isPresent()) {
                weekRecipeService.delete(id);
                return new ResponseEntity<WeekRecipe>(HttpStatus.OK);
            } else
                return new ResponseEntity<WeekRecipe>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<WeekRecipe>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
