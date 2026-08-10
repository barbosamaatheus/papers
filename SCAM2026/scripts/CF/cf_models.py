from dataclasses import dataclass, field, asdict


@dataclass
class CFNodeSnapshot:
    role: str
    node_type: str
    branch: str | None
    text: str | None
    location: dict
    stack_files: list[str] = field(default_factory=list)
    stack_trace: list[dict] = field(default_factory=list)

    def to_dict(self):
        return asdict(self)


@dataclass
class CFConflictSnapshot:
    index: int
    scenario_index: int
    scenario_jar: str | None
    label: str
    event_type: str
    classification: str
    reason: str
    source1: CFNodeSnapshot
    source2: CFNodeSnapshot
    confluence: CFNodeSnapshot
    canonical_signature: dict

    def to_dict(self):
        data = asdict(self)
        data["source1"] = self.source1.to_dict()
        data["source2"] = self.source2.to_dict()
        data["confluence"] = self.confluence.to_dict()
        return data
