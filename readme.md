# Handling Multilingual Entity Description

This project is a POC to confirm management(CREATE, DELETE, UPDATE, READ) of entity description managed in multiple
language.

There is a multi-level entity called **Level** has three levels - level1, level2, level3. Once such dummy entity for
demo is already present in the service layer. This project runs in non-persistent mode. i.e. any change you made will
not be saved. Moreover, you cannot change the existing entity, the output is just a modified copy of the source data.

## GET

### Get all entities in all available language descriptions

`GET http://localhost:8080/levels`

```json
[
  {
    "id": "l1",
    "descriptions": [
      {
        "content": "level one",
        "language": "en"
      },
      {
        "content": "niveau une",
        "language": "fr"
      }
    ],
    "levelTwo": {
      "id": "l11",
      "descriptions": [
        {
          "content": "level two",
          "language": "en"
        },
        {
          "content": "niveau deux",
          "language": "fr"
        }
      ],
      "levelThrees": [
        {
          "id": "l111",
          "descriptions": [
            {
              "content": "level three 1",
              "language": "en"
            },
            {
              "content": "niveau trois 2",
              "language": "fr"
            }
          ]
        },
        {
          "id": "l112",
          "descriptions": [
            {
              "content": "level three 2",
              "language": "en"
            },
            {
              "content": "niveau trois 2",
              "language": "fr"
            }
          ]
        }
      ]
    }
  }
]
```

### Get entity in specific language

`GET http://localhost:8080/levels/l1/language/en`

```json
{
  "descriptions": [
    {
      "content": "level one",
      "language": "en"
    }
  ],
  "levelTwo": {
    "descriptions": [
      {
        "content": "level two",
        "language": "en"
      }
    ],
    "levelThrees": [
      {
        "descriptions": [
          {
            "content": "level three 1",
            "language": "en"
          }
        ]
      },
      {
        "descriptions": [
          {
            "content": "level three 2",
            "language": "en"
          }
        ]
      }
    ]
  }
}
```

## DELETE

### Remove a specific language

`DELETE http://localhost:8080/levels/l1/language/en`

```json
{
  "id": "l1",
  "descriptions": [
    {
      "content": "niveau une",
      "language": "fr"
    }
  ],
  "levelTwo": {
    "id": "l11",
    "descriptions": [
      {
        "content": "niveau deux",
        "language": "fr"
      }
    ],
    "levelThrees": [
      {
        "id": "l111",
        "descriptions": [
          {
            "content": "niveau trois 2",
            "language": "fr"
          }
        ]
      },
      {
        "id": "l112",
        "descriptions": [
          {
            "content": "niveau trois 2",
            "language": "fr"
          }
        ]
      }
    ]
  }
}
```

## UPSERT

# Add new descriptions

`PATH http://localhost:8080/levels/l1/language`

```json
{
  "descriptions": [
    {
      "content": "レベル1",
      "language": "jp"
    }
  ],
  "levelTwo": {
    "id": "l11",
    "descriptions": [
      {
        "content": "レベル2",
        "language": "jp"
      }
    ],
    "levelThrees": [
      {
        "descriptions": [
          {
            "content": "レベル3 1",
            "language": "jp"
          }
        ]
      },
      {
        "descriptions": [
          {
            "content": "レベル3 2",
            "language": "jp"
          }
        ]
      }
    ]
  }
}
```

```json
{
  "id": "l1",
  "descriptions": [
    {
      "content": "level one",
      "language": "en"
    },
    {
      "content": "niveau une",
      "language": "fr"
    },
    {
      "content": "レベル1",
      "language": "jp"
    }
  ],
  "levelTwo": {
    "id": "l11",
    "descriptions": [
      {
        "content": "level two",
        "language": "en"
      },
      {
        "content": "niveau deux",
        "language": "fr"
      },
      {
        "content": "レベル2",
        "language": "jp"
      }
    ],
    "levelThrees": [
      {
        "id": "l111",
        "descriptions": [
          {
            "content": "level three 1",
            "language": "en"
          },
          {
            "content": "niveau trois 2",
            "language": "fr"
          },
          {
            "content": "レベル3 1",
            "language": "jp"
          }
        ]
      },
      {
        "id": "l112",
        "descriptions": [
          {
            "content": "level three 2",
            "language": "en"
          },
          {
            "content": "niveau trois 2",
            "language": "fr"
          },
          {
            "content": "レベル3 2",
            "language": "jp"
          }
        ]
      }
    ]
  }
}
```