import json
import os
import shutil

with open("entity_info.json", "r") as f:
    entity_info = json.load(f)

generated_dir = "../nms/v1_19_2/src/main/kotlin/ru/melonhell/nmsentitylib/nms/v1_19_2/generated"

shutil.rmtree(generated_dir, ignore_errors=True)


def to_camel_case(text):
    return ''.join(i.capitalize() for i in text.split("_"))


for entity_name in entity_info.keys():
    # ебланы на бакките сделали эти классы финальными
    if entity_name in ["hopper_minecart", "spawner_minecart", "tnt_minecart"]:
        continue

    nms_class_name = entity_info[entity_name]["nms_class"].split(".")[-1]
    placeholders = {
        "entity_name": entity_name,
        "entity_name_camel": to_camel_case(entity_name),
        "nms_class": entity_info[entity_name]["nms_class"],
        "nms_class_name": nms_class_name,
        "nms_entity_type": entity_info[entity_name]["nms_entity_type"],
        "bukkit_craft_class_name": entity_info[entity_name]["bukkit_craft_class_name"],
        "bukkit_entity_type": entity_info[entity_name]["bukkit_entity_type"],
        "craftbukkit_version": "v1_19_R1"
    }

    dir_name = f"{generated_dir}/{entity_name}"
    os.makedirs(dir_name, exist_ok=True)
    for template in os.listdir("templates"):
        filename = template
        with open(f"templates/{template}", "r", encoding="utf8") as f:
            template_data = f.read()
        for ph in placeholders.keys():
            template_data = template_data.replace(f"%{ph}%", placeholders[ph])
            filename = filename.replace(f"%{ph}%", placeholders[ph])
        with open(f"{dir_name}/{filename}", "w", encoding="utf8") as f:
            f.write(template_data)
