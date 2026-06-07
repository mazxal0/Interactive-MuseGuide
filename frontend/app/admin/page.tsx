"use client";

import { useState } from "react";
import { Header, Button } from "@/components";

type NodeType = "location" | "passage";
type Floor = "0" | "1" | "2" | "";

type Node = {
    id: string;
    type: NodeType;
    name: string;
    description: string;
    floor: Floor;
};

export default function AdminPage() {
    const [type, setType] = useState<NodeType>("location");
    const [name, setName] = useState("");
    const [description, setDescription] = useState("");
    const [floor, setFloor] = useState<Floor>("");
    const [success, setSuccess] = useState(false);

    const addNode = () => {
        if (!name.trim() || !floor) return;

        const existing = localStorage.getItem("nodes");

        let parsed: Node[] = [];
        try {
            parsed = existing ? JSON.parse(existing) : [];
        } catch {
            parsed = [];
        }

        const newNode: Node = {
            id: crypto.randomUUID(),
            type,
            name,
            description,
            floor,
        };

        parsed.push(newNode);

        localStorage.setItem("nodes", JSON.stringify(parsed));

        setName("");
        setDescription("");
        setFloor("");
        setType("location");

        setSuccess(true);
        setTimeout(() => setSuccess(false), 2500);
    };

    return (
        <div className="flex flex-col gap-5 px-4 py-3">
            <Header headerText="Админ панель — добавление узлов маршрута" />

            {/* TYPE */}
            <div className="flex flex-col gap-2">
                <label>Тип</label>
                <select
                    className="border p-2 rounded"
                    value={type}
                    onChange={(e) =>
                        setType(e.target.value as NodeType)
                    }
                >
                    <option value="location">Локация</option>
                    <option value="passage">Проход</option>
                </select>
            </div>

            {/* NAME */}
            <div className="flex flex-col gap-2">
                <label>Название</label>
                <input
                    className="border p-2 rounded"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    placeholder="Например: Выставка 1 этажа"
                />
            </div>

            {/* DESCRIPTION */}
            <div className="flex flex-col gap-2">
                <label>Описание</label>
                <textarea
                    className="border p-2 rounded"
                    value={description}
                    onChange={(e) =>
                        setDescription(e.target.value)
                    }
                    placeholder="Краткое описание места"
                />
            </div>

            {/* FLOOR */}
            <div className="flex flex-col gap-2">
                <label>Этаж</label>
                <select
                    className="border p-2 rounded"
                    value={floor}
                    onChange={(e) =>
                        setFloor(e.target.value as Floor)
                    }
                >
                    <option value="">
                        Выберите этаж
                    </option>
                    <option value="0">0 этаж</option>
                    <option value="1">1 этаж</option>
                    <option value="2">2 этаж</option>
                </select>
            </div>

            {/* BUTTON */}
            <Button
                onClick={addNode}
                disabled={!name || !floor}
            >
                Добавить
            </Button>

            {/* SUCCESS */}
            {success && (
                <div className="p-3 rounded border bg-green-100 text-green-800">
                    {type === "location"
                        ? "Локация добавлена успешно"
                        : "Проход добавлен успешно"}
                </div>
            )}
        </div>
    );
}